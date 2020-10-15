package com.app.financialplayground.data.home.model


import com.google.gson.annotations.SerializedName

data class Bitcoin(
    @SerializedName("bitstamp")
    val bitstamp: Bitstamp?,
    @SerializedName("blockchain_info")
    val blockchainInfo: BlockchainInfo?,
    @SerializedName("coinbase")
    val coinbase: Coinbase?,
    @SerializedName("foxbit")
    val foxbit: Foxbit?,
    @SerializedName("mercadobitcoin")
    val mercadobitcoin: Mercadobitcoin?
)