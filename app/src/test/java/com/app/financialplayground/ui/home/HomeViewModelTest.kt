package com.app.financialplayground.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.app.financialplayground.MainCoroutineRule
import com.app.financialplayground.data.ViewState
import com.app.financialplayground.data.home.model.*
import com.app.financialplayground.data.home.repository.HomeRepository
import com.app.financialplayground.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery

import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
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
    fun fetchFinancesSuccess_returnsSuccess() = mainCoroutineRule.runBlockingTest {
        val expectedResult = ViewState.Success(getExpectedResults())
        coEvery { homeRepository.getFinances() } returns expectedResult

        homeViewModel.fetchFinances()

        coVerify(exactly = 1) {
            homeRepository.getFinances()
        }

        assertEquals(expectedResult, homeViewModel.viewStateLiveData.getOrAwaitValue())
    }
    
    private fun getExpectedResults() =
        Results(
            availableSources = listOf("BRL"),
            bitcoin = Bitcoin(
                bitstamp = Bitstamp(
                    buy = 11322.09,
                    format = listOf("USD", "en_US"),
                    last = 11322.09,
                    name = "BitStamp",
                    sell = 11321.56,
                    variation = -1.574
                ),
                blockchainInfo = BlockchainInfo(
                    buy = 11325.12,
                    format = listOf("USD", "en_US"),
                    last = 11325.12,
                    name = "Blockchain.info",
                    sell = 11325.12,
                    variation = -1.59
                ),
                coinbase = Coinbase(
                    format = listOf("USD", "en_US"),
                    last = 11324.57,
                    name = "Coinbase",
                    variation = -1.569
                ),
                foxbit = Foxbit(
                    format = listOf("BRL", "pt_BR"),
                    last = 64101.104,
                    name = "FoxBit",
                    variation = -1.383
                ),
                mercadobitcoin = Mercadobitcoin(
                    buy = 64203.06,
                    format = listOf("BRL", "pt_BR"),
                    last = 64203.06,
                    name = "Mercado Bitcoin",
                    sell = 64282.87,
                    variation = -1.376
                )
            ),
            currencies = Currencies(
                aRS = ARS(
                    buy = 0.073,
                    name = "Argentine Peso",
                    sell = null,
                    variation = 0.48
                ),
                bTC = BTC(buy = 67653.957, name = "Bitcoin", sell = 67653.957, variation = -1.596),
                eUR = EUR(buy = 6.6083, name = "Euro", sell = 6.6029, variation = 0.53),
                gBP = GBP(buy = 7.2868, name = "Pound Sterling", sell = null, variation = 0.57),
                source = "BRL",
                uSD = USD(buy = 5.636, name = "Dollar", sell = 5.6359, variation = 0.2)
            ),
            stocks = Stocks(
                cAC = CAC(location = "Paris, French", name = "CAC 40", variation = 2.24),
                iBOVESPA = IBOVESPA(location = "Sao Paulo, Brazil", name = "BM& F BOVESPA",
                points = 98726.812,
                variation = -0.33
            ),
            nASDAQ = NASDAQ(
                location = "New York City, United States",
                name = "NASDAQ Stock Market",
                points = 11777.36,
                variation = 0.54
            ),
            nIKKEI = NIKKEI(location =" Tokyo, Japan", name = "Nikkei 225", variation = -0.41)
        ), taxes = listOf(Taxe(cdi=2.0, cdiDaily=1.9, dailyFactor=1.00007469, date="2020-10-15", selic=2.0, selicDaily=1.9)))
}