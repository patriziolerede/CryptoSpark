package com.cryptospark.data.model

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
    val image: com.cryptospark.data.model.Image,
    @SerializedName("links")
    val links: com.cryptospark.data.model.Links,
    @SerializedName("description")
    val description: com.cryptospark.data.model.Description,
    @SerializedName("sparkline_in_7d")
val sparklineIn7d: com.cryptospark.data.model.SparklineIn7d? = null
)

data class Description(
    @SerializedName("en")
    val en: String
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
    name = "Wellington Cabral da Silva",
    symbol = "BTC",
  description = "",
    image = "",
website = "",
    sparklineData = persistentListOf()
)

