package com.examples.mycapstoneproject.ui.auth

import android.util.Log
import androidx.lifecycle.*
import com.examples.mycapstoneproject.data.datastore.UserPreferences
import com.examples.mycapstoneproject.data.repository.AuthRepository
import com.examples.mycapstoneproject.data.response.LoginResponse
import com.examples.mycapstoneproject.data.response.RefreshResponse
import com.examples.mycapstoneproject.data.response.RegisResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.prefs.Preferences

//,private  val pref: UserPreferences
class AuthViewModel (private val repository: AuthRepository, private val pref: UserPreferences) : ViewModel(){
    companion object{
        private const val TAG = "AuthViewModel"
        private const val USERNAME = "Arief"

    }

    private val _login = MutableLiveData<LoginResponse>()
    val login: LiveData<LoginResponse> = _login
    val regis = MutableLiveData<RegisResponse>()
    val refresh = MutableLiveData<RefreshResponse>()
    val errorMessage = MutableLiveData<String>()
    fun userLogin(email : String, password : String){
        val response = repository.userLogin(email, password)
        response?.enqueue(object: Callback<LoginResponse>{
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ){
//                _isLoading.value = false

                if (response.isSuccessful){
                    _login.value = response.body()

                }else{
                    errorMessage.postValue(response.message())
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                _isLoading.value =false
                errorMessage.postValue(t.message)
                Log.e(TAG, "onFailure: ${t.message}")
            }
        } )


    }
    fun userRegis(name: String,email : String, gender: String,password : String){
        val response = repository.userRegis(name,email,gender,password)
        response?.enqueue(object: Callback<RegisResponse>{
            override fun onResponse(
                call: Call<RegisResponse>,
                response: Response<RegisResponse>
            ){
//                _isLoading.value = false

                if (response.isSuccessful){
                    regis.value = response.body()

                }else{
                    Log.e(TAG, "onFailureess: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<RegisResponse>, t: Throwable) {
//                _isLoading.value =false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        } )


    }
    fun reFresh(token: String){
        val response = repository.refresh(token)
        response?.enqueue(object: Callback<RefreshResponse>{
            override fun onResponse(
                call: Call<RefreshResponse>,
                response: Response<RefreshResponse>
            ){
//                _isLoading.value = false

                if (response.isSuccessful){
                    refresh.value = response.body()

                }else{
                    Log.e(TAG, "onFailureess: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<RefreshResponse>, t: Throwable) {
//                _isLoading.value =false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        } )
    }
    fun saveToken(token:String) {
        viewModelScope.launch {
            pref.saveUser(token)
        }
    }
    fun getToken(): LiveData<String> {
        return pref.getUser().asLiveData()
    }
    fun logout() {
        viewModelScope.launch {
            pref.deleteUser()
        }
    }
    fun saveEmail(email:String) {
        viewModelScope.launch {
            pref.saveEmail(email)
        }
    }
    fun getEmail(): LiveData<String> {
        return pref.getEmail().asLiveData()
    }
    fun delEmail() {
        viewModelScope.launch {
            pref.deleteEmail()
        }
    }
    fun saveName(name:String) {
        viewModelScope.launch {
            pref.saveName(name)
        }
    }
    fun getName(): LiveData<String> {
        return pref.getName().asLiveData()
    }
    fun delName() {
        viewModelScope.launch {
            pref.deleteName()
        }
    }

}