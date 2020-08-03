package com.xunobulax.rambutan.data

import com.xunobulax.rambutan.utilities.*
import kotlinx.coroutines.coroutineScope
import timber.log.Timber
import uk.co.jakebreen.sendgridandroid.SendGrid
import uk.co.jakebreen.sendgridandroid.SendGridMail
import uk.co.jakebreen.sendgridandroid.SendGridResponse
import uk.co.jakebreen.sendgridandroid.SendTask


class Game {

    private lateinit var people: List<Person>
    private lateinit var rules: Map<Long, Long>

    suspend fun startGame(people: List<Person>, rules: Map<Long, Long>) {
        this.people = people
        this.rules = rules
        val pairs = pairPeople()
        sendEmail(pairs)
    }

    private fun pairPeople(): Map<Person, Person> {
        val pairablePeople = people.toMutableList()
        val pairs = mutableMapOf<Person, Person>()

        for (person in people) {
            var randomPerson: Person
            val currentPairPool = pairablePeople.toMutableList()

            do {
                randomPerson = currentPairPool.random()

                if (currentPairPool.size <= 2 && !hasValidPair(person, currentPairPool)) {
                    pairPeople()
                    break
                } else {
                    currentPairPool.remove(randomPerson)
                }
            } while (!validPair(person, randomPerson))

            pairs[person] = randomPerson

            pairablePeople.remove(randomPerson)
        }

        return pairs
    }

    private fun hasValidPair(person: Person, pairPeople: List<Person>): Boolean {
        for (pairPerson in pairPeople) {
            if (validPair(person, pairPerson)) {
                return true
            }
        }
        return false
    }

    private fun validPair(person: Person, target: Person): Boolean {
        return person.id != target.id && target.id != rules[person.id]
    }

    private suspend fun sendEmail(pairs: Map<Person, Person>) {
        val sg =
            SendGrid.create(SENDGRID_API_KEY)

        coroutineScope {
            pairs.forEach { (k, v) ->
                if (k.email!!.contains("@")) {
                    val mail = SendGridMail()
                    with(mail) {
                        addRecipient(k.email!!, k.firstName)
                        setFrom(EMAIL_FROM, NAME_FROM)
                        setSubject(EMAIL_SUBJECT)
                        setContent("You have to buy a gift for ${v.firstName}!")
                    }

                    val task = SendTask(sg, mail)
                    val response: SendGridResponse = task.execute().get()
                    Timber.d(response.code.toString())
                }
            }
        }
//        pairs.forEach { (k, v) -> Timber.d("${k.firstName} buys for ${v.firstName}") }
    }

}