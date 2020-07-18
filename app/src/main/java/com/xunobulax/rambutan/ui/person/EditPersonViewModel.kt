package com.xunobulax.rambutan.ui.person

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xunobulax.rambutan.data.PairRule
import com.xunobulax.rambutan.data.Person
import com.xunobulax.rambutan.repository.PeopleRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate


class EditPersonViewModel @ViewModelInject constructor(private val database: PeopleRepository) : ViewModel() {

    private val _person = MutableLiveData<Person>()
    val person: LiveData<Person> = _person

    private val _partnerName = MutableLiveData<String?>()
    val partnerName: LiveData<String?> = _partnerName

    val partnersList: LiveData<List<Person>> by lazy { database.getPotentialPartnersFor(_person.value?.id ?: 0) }

    private var rule: PairRule? = null

    private val _showBirthdayPickerDialog = MutableLiveData<Boolean?>()
    val showBirthdayPickerDialog: LiveData<Boolean?> = _showBirthdayPickerDialog

    private val _navigateToFamilyFragment = MutableLiveData<Boolean?>()
    val navigateToFamilyFragment: LiveData<Boolean?> = _navigateToFamilyFragment

    private val _navigateToPartnerFragment = MutableLiveData<Boolean?>()
    val navigateToPartnerFragment: LiveData<Boolean?> = _navigateToPartnerFragment

    init {
        _person.value = Person()
    }

    fun loadPerson(personId: Long) = viewModelScope.launch {
        _person.value = database.getPerson(personId)
        _partnerName.value = database.getPartnerNameOf(personId)
    }

    fun getYear(): Int = _person.value!!.birthday?.year ?: LocalDate.now().year

    fun getMonth(): Int = _person.value!!.birthday?.monthValue ?: LocalDate.now().monthValue

    fun getDayOfMonth(): Int = _person.value!!.birthday?.dayOfMonth ?: LocalDate.now().dayOfMonth

    fun onBirthdayClicked() {
        _showBirthdayPickerDialog.value = true
    }

    fun onBirthdaySet(year: Int, month: Int, dayOfMonth: Int) {
        _person.value?.let { person ->
            person.birthday = LocalDate.of(year, month, dayOfMonth)
            _person.value = person
        }
    }

    fun onPartnerClicked() {
        _navigateToPartnerFragment.value = true
    }

    fun onPartnerSet(partner: Person) {
        _partnerName.value = "${partner.firstName} ${partner.lastName}"
        rule = PairRule(partnerId = partner.id, pairable = false)
    }

    fun onSavePerson() {
        GlobalScope.launch {
            _person.value?.let { person ->
                database.savePerson(person, rule)
            }
        }
        _navigateToFamilyFragment.value = true
    }

    fun doneNavigating() {
        _showBirthdayPickerDialog.value = false
        _navigateToPartnerFragment.value = false
        _navigateToFamilyFragment.value = false
    }
}