package com.app.financialplayground.data.home.model


import com.google.gson.annotations.SerializedName

data class Stocks(
    @SerializedName("CAC")
    val cAC: CAC?,
    @SerializedName("IBOVESPA")
    val iBOVESPA: IBOVESPA?,
    @SerializedName("NASDAQ")
    val nASDAQ: NASDAQ?,
    @SerializedName("NIKKEI")
    val nIKKEI: NIKKEI?
)