package com.xunobulax.rambutan.data

import timber.log.Timber


class Game {

    private lateinit var people: List<Person>
    private lateinit var rules: Map<Long, Long>

    fun startGame(people: List<Person>, rules: Map<Long, Long>) {
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

    private fun sendEmail(pairs: Map<Person, Person>) {
        pairs.forEach { (k, v) -> Timber.d("${k.firstName} buys for ${v.firstName}") }
    }

}