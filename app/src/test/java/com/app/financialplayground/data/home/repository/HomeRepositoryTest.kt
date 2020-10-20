package com.app.financialplayground.data.home.repository

import com.app.financialplayground.data.home.api.HomeApiService
import com.app.financialplayground.data.home.model.*
import com.google.gson.annotations.SerializedName
import com.haroldadmin.cnradapter.NetworkResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

@ExperimentalCoroutinesApi
class HomeRepositoryTest {

    private lateinit var repository: HomeRepository
    @MockK
    lateinit var service: HomeApiService

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = HomeRepository(service)
    }


    @Test
    fun getFinances_callsService() = runBlockingTest {
        val response = NetworkResponse.Success(getFinances(), null, 200)
        coEvery { service.getFinances() } returns response

        repository.getFinances()

        coVerify(exactly = 1) {
            service.getFinances()
        }
    }

    private fun getFinances() =
        Finance(
            by = "",
            executionTime = 1.0,
            fromCache = false,
            results = getExpectedResults(),
            validKey = true
        )

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