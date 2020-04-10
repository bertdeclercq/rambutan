package com.xunobulax.rambutan.data


class Family {

    private val _members = mutableListOf<Person>()

    val members: List<Person>
        get() = _members

    fun addMember(person: Person) {
        _members.add(person)
    }

}