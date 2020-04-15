package com.xunobulax.rambutan.ui.family

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.xunobulax.rambutan.data.AppDatabase
import com.xunobulax.rambutan.data.Person

class FamilyViewModel(application: Application) : AndroidViewModel(application) {

    private val personDao = AppDatabase.getDatabase(application.applicationContext).personDao()

    val people: LiveData<List<Person>> = personDao.getPeople()

    fun addPerson() {

    }
}
