package com.cryptospark.data.model

import com.cryptospark.ui.models.MarketDisplayable
import com.google.gson.annotations.SerializedName
import kotlinx.collections.immutable.persistentListOf

data class Market(
    @SerializedName("id")
    val id: String,
    val symbol: String? = null,
    val name: String? = null,
    val image: String? = null,
    @SerializedName("current_price")
    val currentPrice: Double? = null,
    @SerializedName("sparkline_in_7d")
    val sparklineIn7d: SparklineIn7d? = null
)


data class SparklineIn7d(
    val price: List<Double>? = null
)

fun buildMarketPreview() = MarketDisplayable(
    id = "bitcoin",
    image = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1696501400",
    sparklineData = persistentListOf()
)