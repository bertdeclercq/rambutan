package com.xunobulax.rambutan.ui.person

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xunobulax.rambutan.data.AppDatabase
import com.xunobulax.rambutan.data.Person
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDate


class AddPersonViewModel(application: Application) : AndroidViewModel(application) {

    private val personDao = AppDatabase.getDatabase(application.applicationContext).personDao()

    val person = Person()

    private val _navigateToFamilyFragment = MutableLiveData<Boolean?>()
    val navigateToFamilyFragment: LiveData<Boolean?>
        get() = _navigateToFamilyFragment

    private val _navigateToBirthdayPickerDialog = MutableLiveData<Boolean?>()
    val navigateToBirthdayPickerDialog: LiveData<Boolean?>
        get() = _navigateToBirthdayPickerDialog

    fun setBirthday(year: Int, month: Int, dayOfMonth: Int) {
        person.birthday = LocalDate.of(year, month, dayOfMonth)
    }

    fun onAddPerson() {
        viewModelScope.launch {
            personDao.insertPerson(person)
        }
        _navigateToFamilyFragment.value = true
    }

    fun onBirthdayClicked() {
        _navigateToBirthdayPickerDialog.value = true
    }

}