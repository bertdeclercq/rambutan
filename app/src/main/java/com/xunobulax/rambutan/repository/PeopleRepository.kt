package com.xunobulax.rambutan.repository

import androidx.lifecycle.LiveData
import com.xunobulax.rambutan.data.AppDatabase
import com.xunobulax.rambutan.data.PairRule
import com.xunobulax.rambutan.data.Person
import javax.inject.Inject


class PeopleRepository @Inject constructor(appDatabase: AppDatabase) {

    private val personDao = appDatabase.personDao()
    private val pairRuleDao = appDatabase.pairRuleDao()

    fun getPeople(): LiveData<List<Person>> = personDao.getPeople()

    fun getPotentialPartnersFor(personId: Long): LiveData<List<Person>> =
        if (personId > 0) {
            personDao.getPotentialPartnersFor(personId)
        } else {
            personDao.getPeople()
        }

    suspend fun getPerson(personId: Long) = personDao.getPerson(personId)

    suspend fun getPartnerNameOf(personId: Long): String {
        val partnerId = pairRuleDao.getPartnerId(personId)
        if (partnerId != null) {
            val partner = personDao.getPerson(partnerId)
            return "${partner.firstName} ${partner.lastName}"
        }
        return ""
    }

    suspend fun savePerson(person: Person, rule: PairRule?) {
        val personId = if (person.id > 0L) {
            updatePerson(person)
            person.id
        } else {
            insertPerson(person)!!
        }

        if (rule != null) {
            rule.personId = personId
            insertRule(rule)
        }
    }

    private suspend fun insertPerson(person: Person) = personDao.insertPerson(person)

    private suspend fun updatePerson(person: Person) {
        personDao.updatePerson(person)
    }

    suspend fun getPairRulesMap(): Map<Long, Long> {
        val rules = pairRuleDao.getPairRules()
        return rules.map { it.personId to it.partnerId }.toMap()
    }

    private suspend fun insertRule(rule: PairRule) {
        // Check if person already has rules
        val personRules = pairRuleDao.getPairRulesFor(rule.personId)

        if (personRules != null) {
            pairRuleDao.deletePairRules(personRules)
        }

        // Check if partner already has rules
        val partnerRules = pairRuleDao.getPairRulesFor(rule.partnerId)

        if (partnerRules != null) {
            pairRuleDao.deletePairRules(partnerRules)
        }
        pairRuleDao.insertPairRules(rule, reverseRule(rule))
    }

    private fun reverseRule(rule: PairRule): PairRule =
        PairRule(personId = rule.partnerId, partnerId = rule.personId, pairable = rule.pairable)

    suspend fun deletePerson(person: Person) {
        if (person.id > 0) {
            personDao.deletePerson(person)
        }
    }

}