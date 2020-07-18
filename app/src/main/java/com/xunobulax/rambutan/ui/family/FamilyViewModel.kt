package com.xunobulax.rambutan.ui.family

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xunobulax.rambutan.data.Person
import com.xunobulax.rambutan.repository.PeopleRepository


class FamilyViewModel @ViewModelInject constructor(private val database: PeopleRepository) : ViewModel() {

    val people: LiveData<List<Person>> by lazy { database.getPeople() }

}
