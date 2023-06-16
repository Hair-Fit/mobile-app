package com.examples.mycapstoneproject.ui.camera

import androidx.lifecycle.*
import com.examples.mycapstoneproject.data.api.ApiConfig
import com.examples.mycapstoneproject.data.datastore.UserPreferences
import com.examples.mycapstoneproject.data.response.ListResponse
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CameraViewModel(private val pref: UserPreferences) : ViewModel( ) {
    private val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message
    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    fun uploadImage(token:String, imageMultipart : MultipartBody.Part, description:String ) {
        _isLoading.value = true
        val description = description.toRequestBody("text/plain".toMediaType())
        val imageMultipart = imageMultipart
        val apiService = ApiConfig.getApiService()

        val uploadImageRequest = apiService.uploadImage("bearer ${token}",imageMultipart)
        uploadImageRequest.enqueue(object : Callback<ListResponse> {
            override fun onResponse(
                call: Call<ListResponse>,
                response: Response<ListResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null ) {

                        _isSuccess.value = true
                    }
                } else {
                    _message.value = response.message()
                    _isSuccess.value = false
                }
            }

            override fun onFailure(call: Call<ListResponse>, t: Throwable) {
                _message.value =  t.message
                _isSuccess.value = false
                _isLoading.value = false
            }
        })
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
}