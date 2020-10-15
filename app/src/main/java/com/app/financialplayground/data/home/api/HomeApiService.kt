package com.app.financialplayground.data.home.api

import com.app.financialplayground.data.ApiEndPoint.BASE
import com.app.financialplayground.data.home.model.Finance
import com.haroldadmin.cnradapter.NetworkResponse
import okhttp3.ResponseBody
import retrofit2.http.GET

interface HomeApiService {
    @GET(BASE)
    suspend fun getFinances(): NetworkResponse<Finance, ResponseBody>
}