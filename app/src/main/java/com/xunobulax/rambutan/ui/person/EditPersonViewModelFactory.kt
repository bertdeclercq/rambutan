package com.xunobulax.rambutan.ui.person

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xunobulax.rambutan.repositories.PeopleRepository


class EditPersonViewModelFactory(private val database: PeopleRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditPersonViewModel::class.java)) {
            return EditPersonViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}