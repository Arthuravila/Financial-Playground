package com.app.financialplayground.data.home.repository

import com.app.financialplayground.data.home.api.HomeApiService
import com.app.financialplayground.data.home.model.Finance
import com.haroldadmin.cnradapter.NetworkResponse
import okhttp3.ResponseBody

class HomeRepository (private val apiService: HomeApiService) {
    suspend fun getFinances(): NetworkResponse<Finance, ResponseBody> {
        return apiService.getFinances()
    }
}
