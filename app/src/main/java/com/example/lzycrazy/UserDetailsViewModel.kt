package com.example.lzycrazy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserDetailsViewModel : ViewModel() {

    private val _email = MutableLiveData("")
    val email: LiveData<String> get() = _email

    private val _phone = MutableLiveData("")
    val phone: LiveData<String> get() = _phone

    private val _gender = MutableLiveData("")
    val gender: LiveData<String> get() = _gender

    private val _businessLink = MutableLiveData("")
    val businessLink: LiveData<String> get() = _businessLink

    private val _country = MutableLiveData("")
    val country: LiveData<String> get() = _country

    private val _state = MutableLiveData("")
    val state: LiveData<String> get() = _state

    private val _city = MutableLiveData("")
    val city: LiveData<String> get() = _city

    private val _neighbour = MutableLiveData("")
    val neighbour: LiveData<String> get() = _neighbour

    private val _dob = MutableLiveData("")
    val dob: LiveData<String> get() = _dob

    fun saveDetails(
        email: String,
        phone: String,
        gender: String,
        businessLink: String,
        country: String,
        state: String,
        city: String,
        neighbour: String,
        dob: String
    ) {
        _email.value = email
        _phone.value = phone
        _gender.value = gender
        _businessLink.value = businessLink
        _country.value = country
        _state.value = state
        _city.value = city
        _neighbour.value = neighbour
        _dob.value = dob
    }
}
