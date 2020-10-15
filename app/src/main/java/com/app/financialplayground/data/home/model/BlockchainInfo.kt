package com.app.financialplayground.data.home.model


import com.google.gson.annotations.SerializedName

data class BlockchainInfo(
    @SerializedName("buy")
    val buy: Double?,
    @SerializedName("format")
    val format: List<String>?,
    @SerializedName("last")
    val last: Double?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("sell")
    val sell: Double?,
    @SerializedName("variation")
    val variation: Double?
)