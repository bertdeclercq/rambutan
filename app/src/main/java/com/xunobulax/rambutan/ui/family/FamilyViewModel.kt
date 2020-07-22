package com.xunobulax.rambutan.ui.family

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xunobulax.rambutan.data.Game
import com.xunobulax.rambutan.data.Person
import com.xunobulax.rambutan.repository.PeopleRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.random.Random


class FamilyViewModel @ViewModelInject constructor(private val database: PeopleRepository) :
    ViewModel() {

    val people: LiveData<List<Person>> by lazy { database.getPeople() }

    fun onStartGame() {
        if (people.value == null) {
            Timber.d("Will return")
            return
        }

        GlobalScope.launch {
            Game().startGame(people.value!!, database.getPairRulesMap())
        }
    }

}
