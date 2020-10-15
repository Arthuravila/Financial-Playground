package com.app.financialplayground.data.home.model


import com.google.gson.annotations.SerializedName

data class Foxbit(
    @SerializedName("format")
    val format: List<String>?,
    @SerializedName("last")
    val last: Double?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("variation")
    val variation: Double?
)