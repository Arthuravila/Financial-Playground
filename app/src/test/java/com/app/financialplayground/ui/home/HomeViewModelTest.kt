package com.app.financialplayground.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.app.financialplayground.MainCoroutineRule
import com.app.financialplayground.data.home.model.Finance
import com.app.financialplayground.data.home.model.Results
import com.app.financialplayground.data.home.repository.HomeRepository
import com.app.financialplayground.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery

import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    // Subject under test
    private lateinit var homeViewModel: HomeViewModel

    private val homeRepository = mockk<HomeRepository>(relaxed = true)

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        homeViewModel = HomeViewModel(homeRepository)
    }

    @Test
    fun fetchFinances_getInfo() = mainCoroutineRule.runBlockingTest {
        coEvery { homeRepository.getFinances() }.returns(MutableLiveData(getExpectedResults()))

        homeViewModel.fetchFinances()

        coVerify { homeRepository.getFinances() }

        assertThat(homeViewModel.financeLiveData.getOrAwaitValue()).isEqualTo(getExpectedResults())
    }


    private fun getExpectedResults() =
        Results(
            listOf("BR"),
            null,
            null,
            null,
            null
        )
}