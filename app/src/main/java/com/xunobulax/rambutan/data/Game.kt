package com.xunobulax.rambutan.data

import timber.log.Timber


class Game {

    suspend fun startGame(people: List<Person>, rules: Map<Long, Long>) {
        val pairs = pairPeople(people, rules)
        sendEmail(pairs)
    }

    private fun pairPeople(people: List<Person>, rules: Map<Long, Long>): Map<Person, Person> {
        val pairPeople: MutableList<Person> = people.toMutableList()
        val pairs = mutableMapOf<Person, Person>()

        for (person in people) {
            var randomPerson: Person

            do {
                randomPerson = pairPeople.random()
            } while (!validatePair(person, randomPerson, rules[person.id]))

            pairs[person] = randomPerson

            pairPeople.remove(randomPerson)
        }
        return pairs
    }

    private fun validatePair(person: Person, target: Person, rule: Long?): Boolean {
        return person.id != target.id && target.id != rule
    }

    private suspend fun sendEmail(pairs: Map<Person, Person>) {
        pairs.forEach { (k, v) -> Timber.d("${k.firstName} buys for ${v.firstName}")}
    }

}