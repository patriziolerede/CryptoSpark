package com.cryptospark.data

import com.cryptospark.data.model.CoinDetail
import com.cryptospark.data.model.Market
import com.cryptospark.ui.models.CoinDetailDisplayable
import com.cryptospark.ui.models.MarketDisplayable

interface CoingeckoRepository {
    suspend fun getMarkets(): Result<List<MarketDisplayable>>
    suspend fun getCoinDetail(coinId: String): Result<CoinDetailDisplayable?>
}
