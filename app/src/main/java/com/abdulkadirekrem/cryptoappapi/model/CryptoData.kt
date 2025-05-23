package com.abdulkadirekrem.cryptoappapi.model

import com.google.gson.annotations.SerializedName

data class CryptoData(
    @SerializedName("id") val id: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,
    @SerializedName("current_price") val currentPrice: Double,
    @SerializedName("market_cap") val marketCap: Long,
    @SerializedName("market_cap_rank") val marketCapRank: Int,
    @SerializedName("price_change_percentage_24h") val priceChangePercentage24h: Double?
)