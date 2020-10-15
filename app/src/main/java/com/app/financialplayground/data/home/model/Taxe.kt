package com.app.financialplayground.data.home.model


import com.google.gson.annotations.SerializedName

data class Taxe(
    @SerializedName("cdi")
    val cdi: Double?,
    @SerializedName("cdi_daily")
    val cdiDaily: Double?,
    @SerializedName("daily_factor")
    val dailyFactor: Double?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("selic")
    val selic: Double?,
    @SerializedName("selic_daily")
    val selicDaily: Double?
)