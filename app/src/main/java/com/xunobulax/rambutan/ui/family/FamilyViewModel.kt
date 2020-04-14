package com.xunobulax.rambutan.ui.family

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xunobulax.rambutan.data.Person
import com.xunobulax.rambutan.data.PersonDao

class FamilyViewModel(personDao: PersonDao) : ViewModel() {

    val people: LiveData<List<Person>> = personDao.getPeople()
}
