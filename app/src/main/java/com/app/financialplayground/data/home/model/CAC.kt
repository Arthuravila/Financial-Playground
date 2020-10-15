package com.app.financialplayground.data.home.model


import com.google.gson.annotations.SerializedName

data class CAC(
    @SerializedName("location")
    val location: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("variation")
    val variation: Double?
)