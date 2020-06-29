package com.xunobulax.rambutan.ui.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xunobulax.rambutan.data.Person
import com.xunobulax.rambutan.repositories.PeopleRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate


class EditPersonViewModel(private val database: PeopleRepository) : ViewModel() {

    private val _person = MutableLiveData<Person>()
    val person: LiveData<Person> = _person

    private val _partnerName = MutableLiveData<String?>()
    val partnerName: LiveData<String?> = _partnerName

    val partnersList: LiveData<List<Person>> by lazy { database.getPotentialPartners() }

    private var partner: Person? = null

    private var oldPartner: Person? = null

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
        _person.value?.let { person ->
            if (person.hasPartner()) {
                partner = database.getPerson(person.partnerId!!)
                _partnerName.value = "${partner!!.firstName} ${partner!!.lastName}"
            }
        }
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
        _person.value?.let { person ->
            if (person.hasPartner()) {
                oldPartner = partner
            }
            person.partnerId = partner.id
        }
        this.partner = partner
    }

    fun onSavePerson() {
        GlobalScope.launch {
            _person.value?.let { person ->
                if (person.id > 0L) {
                    updatePerson(person)

                    if (oldPartner != null) {
                        oldPartner!!.partnerId = null
                        updatePerson(oldPartner!!)
                    }
                } else {
                    insertPerson(person)
                }
            }
        }
        _navigateToFamilyFragment.value = true
    }

    private suspend fun insertPerson(person: Person) {
        database.insertPerson(person)
    }

    private suspend fun updatePerson(person: Person) {
        database.updatePerson(person)
    }

    fun doneNavigating() {
        _showBirthdayPickerDialog.value = false
        _navigateToPartnerFragment.value = false
        _navigateToFamilyFragment.value = false
    }
}