package com.examples.mycapstoneproject.data.repository

import com.examples.mycapstoneproject.data.api.ApiConfig
import com.examples.mycapstoneproject.data.api.ApiService

class CatalogRepository {
    private var apiInterface: ApiService?=null

    init {
        apiInterface = ApiConfig.getApiService()
    }

    fun getList(token: String) = apiInterface?.getList(token)


}