package com.xunobulax.rambutan.ui.family

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xunobulax.rambutan.data.PersonDao
import com.xunobulax.rambutan.repositories.PeopleRepository


class FamilyViewModelFactory(private val database: PeopleRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FamilyViewModel::class.java)) {
            return FamilyViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}