package com.app.financialplayground.data.home.model


import com.google.gson.annotations.SerializedName

data class Currencies(
    @SerializedName("ARS")
    val aRS: ARS?,
    @SerializedName("BTC")
    val bTC: BTC?,
    @SerializedName("EUR")
    val eUR: EUR?,
    @SerializedName("GBP")
    val gBP: GBP?,
    @SerializedName("source")
    val source: String?,
    @SerializedName("USD")
    val uSD: USD?
)