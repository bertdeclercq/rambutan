package com.xunobulax.rambutan.ui.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xunobulax.rambutan.data.Person
import com.xunobulax.rambutan.data.PersonDao
import kotlinx.coroutines.launch
import java.time.LocalDate


class AddPersonViewModel(private val database: PersonDao) : ViewModel() {

    var person: Person = Person()

    private lateinit var partner: Person

    val people: LiveData<List<Person>> by lazy { database.getPeople() }

    private val _showBirthdayPickerDialog = MutableLiveData<Boolean?>()
    val showBirthdayPickerDialog: LiveData<Boolean?>
        get() = _showBirthdayPickerDialog

    private val _navigateToFamilyFragment = MutableLiveData<Boolean?>()
    val navigateToFamilyFragment: LiveData<Boolean?>
        get() = _navigateToFamilyFragment

    private val _navigateToPartnerFragment = MutableLiveData<Boolean?>()
    val navigateToPartnerFragment: LiveData<Boolean?>
        get() = _navigateToPartnerFragment

    fun loadPerson(personId: Long) = viewModelScope.launch {
        person = database.getPerson(personId)
        if (person.hasPartner()) {
            partner = database.getPerson(person.partnerId!!)
        }
    }

    fun setBirthday(year: Int, month: Int, dayOfMonth: Int) {
        person.birthday = LocalDate.of(year, month, dayOfMonth)
    }

    fun getPartnerName(): String {
        return if (person.hasPartner()) {
            "${partner.firstName} ${partner.lastName}"
        } else {
            ""
        }
    }

    fun getYear(): Int = person.birthday?.year ?: LocalDate.now().year

    fun getMonth(): Int = person.birthday?.monthValue ?: LocalDate.now().monthValue

    fun getDayOfMonth(): Int = person.birthday?.dayOfMonth ?: LocalDate.now().dayOfMonth

    fun onBirthdayClicked() {
        _showBirthdayPickerDialog.value = true
    }

    fun onPartnerClicked() {
        _navigateToPartnerFragment.value = true
    }

    fun onPartnerPicked(person: Person) {
        validatePartner(person)
    }

    private fun validatePartner(partner: Person) {
        //TODO Show a dialog to confirm changing the partner (confirmation, navigate)
        this.partner = partner
        person.partnerId = partner.id
    }

    fun onAddPerson() = viewModelScope.launch {
        val newPersonId = database.insertPerson(person)
        if (person.hasPartner()) {
            partner.partnerId = newPersonId
            database.updatePerson(partner)
        }
        _navigateToFamilyFragment.value = true
    }

    fun doneNavigating() {
        _showBirthdayPickerDialog.value = false
        _navigateToPartnerFragment.value = false
        _navigateToFamilyFragment.value = false
    }
}