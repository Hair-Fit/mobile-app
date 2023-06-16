package com.examples.mycapstoneproject.data.repository

import com.examples.mycapstoneproject.data.api.ApiConfig
import com.examples.mycapstoneproject.data.api.ApiService

class AuthRepository(private val apiService: ApiService) {


    fun userLogin(email: String,password: String)= apiService.login(email, password)

    fun userRegis(name: String,email: String,gender: String,password: String)= apiService.register(name, email, gender, password)

    fun refresh(token: String)= apiService.refresh(token)



}