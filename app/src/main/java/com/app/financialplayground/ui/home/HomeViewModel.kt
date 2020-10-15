package com.app.financialplayground.ui.home

import androidx.lifecycle.*
import com.app.financialplayground.data.home.model.Finance
import com.app.financialplayground.data.home.model.Results
import com.app.financialplayground.data.home.repository.HomeRepository
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _finances = MutableLiveData<Finance>()
    private val _items: LiveData<Results> = Transformations.map(_finances) {
        it.results
    }
    val items: LiveData<Results> = _items

    init {
        getEvents()
    }

    private fun getEvents() {
        viewModelScope.launch {
            when (val response = homeRepository.getFinances()) {
                is NetworkResponse.Success -> {
                    _finances.postValue(response.body)
                }
                is NetworkResponse.ServerError -> {

                }
                else -> {

                }
            }
        }
    }
}