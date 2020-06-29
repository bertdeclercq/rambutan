package com.xunobulax.rambutan.ui.family

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xunobulax.rambutan.data.Person
import com.xunobulax.rambutan.repositories.PeopleRepository


class FamilyViewModel(database: PeopleRepository) : ViewModel() {

    val people: LiveData<List<Person>> by lazy { database.getPeople() }

}
