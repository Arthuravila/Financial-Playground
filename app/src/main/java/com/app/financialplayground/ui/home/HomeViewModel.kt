package com.app.financialplayground.ui.home

import androidx.lifecycle.*
import com.app.financialplayground.data.ViewState
import com.app.financialplayground.data.home.model.Results
import com.app.financialplayground.data.home.repository.HomeRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _viewStateLiveData: MutableLiveData<ViewState<Results>> = MutableLiveData()
    val viewStateLiveData = Transformations.map(_viewStateLiveData) { it }

    fun fetchFinances() {
        _viewStateLiveData.value = ViewState.Loading
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            onError(exception.message)
        }
        viewModelScope.launch(coroutineExceptionHandler) {
            withContext(Dispatchers.IO) {
                val result = homeRepository.getFinances()
                withContext(Dispatchers.Main) {
                    _viewStateLiveData.value = result
                }
            }
        }
    }

    private fun onError(message: String?) {
        _viewStateLiveData.value = ViewState.Error(message)
    }
}
