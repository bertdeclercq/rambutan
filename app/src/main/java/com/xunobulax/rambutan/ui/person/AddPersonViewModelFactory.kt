package com.xunobulax.rambutan.ui.person

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xunobulax.rambutan.data.PersonDao


class AddPersonViewModelFactory(private val database: PersonDao) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddPersonViewModel::class.java)) {
            return AddPersonViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}