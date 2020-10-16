package com.app.financialplayground.data

sealed class ViewState<out T : Any> {
    class Success<out T : Any>(val value: T) : ViewState<T>()

    class Error(val message: String?) : ViewState<Nothing>()

    object Loading : ViewState<Nothing>()
}