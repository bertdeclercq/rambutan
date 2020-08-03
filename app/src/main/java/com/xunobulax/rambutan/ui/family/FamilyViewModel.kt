package com.xunobulax.rambutan.ui.family

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xunobulax.rambutan.data.Game
import com.xunobulax.rambutan.data.Person
import com.xunobulax.rambutan.repository.PeopleRepository
import kotlinx.coroutines.launch


class FamilyViewModel @ViewModelInject constructor(private val database: PeopleRepository) :
    ViewModel() {

    val people: LiveData<List<Person>> by lazy { database.getPeople() }

    private lateinit var rules: Map<Long, Long>

    fun onStartGame() = viewModelScope.launch {
        getRules().join()
        Game().startGame(people.value!!, rules)
    }

    private fun getRules() = viewModelScope.launch {
        rules = database.getPairRulesMap()
    }

}
