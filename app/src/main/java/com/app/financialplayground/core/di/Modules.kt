package com.app.financialplayground.core.di

import com.app.financialplayground.data.RetrofitInitializer
import com.app.financialplayground.data.home.repository.HomeRepository
import com.app.financialplayground.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelModule = module {
    viewModel { HomeViewModel(get()) }

}

private val networkModule = module {
     single { RetrofitInitializer().createEventsApiService() }
}

private val repositoryModule = module {
     factory { HomeRepository(get()) }
}

val appModules = listOf(viewModelModule, repositoryModule, networkModule)
