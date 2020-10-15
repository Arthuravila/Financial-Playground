package com.app.financialplayground.data.home.model


import com.google.gson.annotations.SerializedName

data class USD(
    @SerializedName("buy")
    val buy: Double?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("sell")
    val sell: Double?,
    @SerializedName("variation")
    val variation: Double?
)