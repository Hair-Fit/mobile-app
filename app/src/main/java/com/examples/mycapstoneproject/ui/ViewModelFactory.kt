package com.examples.mycapstoneproject.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.examples.mycapstoneproject.data.datastore.UserPreferences
import com.examples.mycapstoneproject.data.repository.AuthRepository
import com.examples.mycapstoneproject.ui.auth.AuthViewModel


class ViewModelFactory (private val AuthRepository: AuthRepository, private val pref: UserPreferences) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(AuthRepository,pref) as T

        }


        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}