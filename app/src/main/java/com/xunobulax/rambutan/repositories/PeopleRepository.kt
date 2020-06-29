package com.xunobulax.rambutan.repositories

import androidx.lifecycle.LiveData
import com.xunobulax.rambutan.data.Person
import com.xunobulax.rambutan.data.PersonDao


class PeopleRepository(private val database: PersonDao) {

    fun getPeople(): LiveData<List<Person>> = database.getPeople()

    fun getPotentialPartners(): LiveData<List<Person>> = database.getPeople()

    suspend fun getPerson(personId: Long) = database.getPerson(personId)

    suspend fun insertPerson(person: Person) {
        val personId = database.insertPerson(person)

        updateLinkedPeople(personId)
    }

    suspend fun updatePerson(person: Person) {
        database.updatePerson(person)

        updateLinkedPeople(person.id)
    }

    private suspend fun updatePartner(personId: Long, partnerId: Long?) {
        val person = database.getPerson(personId)

        person.partnerId = partnerId
        database.updatePerson(person)
    }

    private suspend fun updateLinkedPeople(personId: Long?) {
        if (personId != null && personId > 0) {

        }
    }

}