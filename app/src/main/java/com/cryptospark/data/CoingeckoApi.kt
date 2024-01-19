package com.cryptospark.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoingeckoApi {
    @GET(Endpoints.GET_MARKETS)
    suspend fun getMarkets(
        @Query("vs_currency") currency: String = "eur",
        @Query("page") page: Int = 1,
        @Query("per_page") numCoinsPerPage: Int = 10,
        @Query("order") order: String = "market_cap_desc",
        @Query("sparkline") includeSparkline7dData: Boolean = true,
        @Query("price_change_percentage") priceChangePercentageIntervals: String = "",
        @Query("ids") coinIds: String? = null
    ): List<com.cryptospark.data.model.Market>

    @GET(Endpoints.GET_DETAIL)
    suspend fun getDetail(
        @Path("id") coinId: String,
        @Query("sparkline") includeSparkline7dData: Boolean = true
    ): com.cryptospark.data.model.CoinDetail?


}
