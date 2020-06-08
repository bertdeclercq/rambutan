package com.xunobulax.rambutan.ui.person

import androidx.lifecycle.*
import com.xunobulax.rambutan.data.Person
import com.xunobulax.rambutan.data.PersonDao
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate


class AddPersonViewModel(private val database: PersonDao) : ViewModel() {

    private val _person = MutableLiveData<Person>()
    val person: LiveData<Person> = _person

    private val _partner = MutableLiveData<Person>()
    val partner: LiveData<Person> = _partner

    val people: LiveData<List<Person>> by lazy { database.getPeople() }

    private val _showBirthdayPickerDialog = MutableLiveData<Boolean?>()
    val showBirthdayPickerDialog: LiveData<Boolean?> = _showBirthdayPickerDialog

    private val _navigateToFamilyFragment = MutableLiveData<Boolean?>()
    val navigateToFamilyFragment: LiveData<Boolean?> = _navigateToFamilyFragment

    private val _navigateToPartnerFragment = MutableLiveData<Boolean?>()
    val navigateToPartnerFragment: LiveData<Boolean?> = _navigateToPartnerFragment

    init {
        _person.value = Person()
        _partner.value = Person()
    }

    fun loadPerson(personId: Long) = viewModelScope.launch {
        _person.value = database.getPerson(personId)
        _person.value?.let { person ->
            if (person.hasPartner()) {
                coroutineScope {
                    _partner.value = database.getPerson(person.partnerId!!)
                }
            }
        }
    }

    fun setBirthday(year: Int, month: Int, dayOfMonth: Int) {
        _person.value?.let { person ->
            person.birthday = LocalDate.of(year, month, dayOfMonth)
            _person.value = person
        }
    }

    fun getYear(): Int = _person.value!!.birthday?.year ?: LocalDate.now().year

    fun getMonth(): Int = _person.value!!.birthday?.monthValue ?: LocalDate.now().monthValue

    fun getDayOfMonth(): Int = _person.value!!.birthday?.dayOfMonth ?: LocalDate.now().dayOfMonth

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
        _partner.value = partner
        _person.value!!.partnerId = partner.id
    }

    fun onAddPerson() = viewModelScope.launch {
        _person.value?.let { person ->
            var personId: Long = person.id
            if (personId > 0L) {
                updatePerson(person)
            } else {
                personId = insertPerson(person)!!
            }
            if (person.hasPartner()) {
                updatePartner(_partner.value!!, personId)
            }
            _navigateToFamilyFragment.value = true
        }
    }

    private suspend fun updatePerson(person: Person) {
        database.updatePerson(person)
    }

    private suspend fun insertPerson(person: Person) = database.insertPerson(person)

    private suspend fun updatePartner(partner: Person, currentPersonId: Long) {
        partner.partnerId = currentPersonId
        database.updatePerson(partner)
    }

    fun doneNavigating() {
        _showBirthdayPickerDialog.value = false
        _navigateToPartnerFragment.value = false
        _navigateToFamilyFragment.value = false
    }
}