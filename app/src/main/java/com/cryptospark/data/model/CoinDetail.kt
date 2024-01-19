package com.cryptospark.data.model

import android.text.Spanned
import androidx.core.text.toSpanned
import com.cryptospark.ui.models.CoinDetailDisplayable
import com.google.gson.annotations.SerializedName
import kotlinx.collections.immutable.persistentListOf

data class CoinDetail (
    @SerializedName("id")
    val id: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: Image,
    @SerializedName("links")
    val links: Links,
    @SerializedName("description")
    val description: Description,

    @SerializedName("market_data")
val marketData: MarketData? = null
)

data class Description(
    @SerializedName("en")
    val en: String
)

data class MarketData(
    @SerializedName("sparkline_7d")
    val sparkline7d: SparklineIn7d? = null
)

data class Links(
    @SerializedName("homepage")
    val homepage: List<String>,
    @SerializedName("whitepaper")
    val whitepaper: String,
    @SerializedName("blockchain_site")
    val blockchainSite: List<String>
)
data class Image(
    @SerializedName("thumb")
    val thumb: String,
    @SerializedName("small")
    val small: String,
    @SerializedName("large")
    val large: String
)


fun buildCoinDetailPreview() = CoinDetailDisplayable(
   id="BTC",
    name = "Bitcoin",
    symbol = "BTC",
  description = "descriptio".toSpanned(),
    image = "",
website = "",
    sparklineData = persistentListOf()
)

