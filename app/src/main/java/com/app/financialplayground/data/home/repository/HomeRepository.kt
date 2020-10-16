package com.app.financialplayground.data.home.repository

import com.app.financialplayground.data.home.api.HomeApiService
import com.app.financialplayground.data.home.model.Results
import com.app.financialplayground.data.ViewState
import com.haroldadmin.cnradapter.NetworkResponse

class HomeRepository(private val apiService: HomeApiService) {

    suspend fun getFinances(): ViewState<Results> {
        return when (val response = apiService.getFinances()) {
            is NetworkResponse.Success -> {
                val results = response.body.results
                ViewState.Success(results)
            }
            is NetworkResponse.ServerError -> {
                val message = handleError(response.code)
                ViewState.Error(message)
            }
            is NetworkResponse.NetworkError -> {
                val message = response.error.localizedMessage
                ViewState.Error(message)
            }
            is NetworkResponse.UnknownError -> {
                val message = response.error.localizedMessage
                ViewState.Error(message)
            }
        }
    }

    private fun handleError(code: Int) =
        when (code) {
            403 -> "Access to resource is forbidden"
            404 -> "Resource not found"
            500 -> "Internal server error"
            502 -> "Bad Gateway"
            301 -> "Resource has been removed permanently"
            302 -> "Resource moved, but has been found"
            else -> "All cases have not been covered!"
        }
}
