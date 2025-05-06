package com.abdulkadirekrem.cryptoappapi.network
import com.abdulkadirekrem.cryptoappapi.model.CryptoData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApiService {
    @GET("coins/markets")
    suspend fun getCryptoData(
        @Query("vs_currency") currency: String = "usd",
        @Query("order") order: String = "market_cap_desc",
        @Query("per_page") perPage: Int = 16,// Kaç tane coin listelenecek ?
        @Query("page") page: Int = 1,
        @Query("sparkline") sparkline: Boolean = false
    ): Response<List<CryptoData>>
}