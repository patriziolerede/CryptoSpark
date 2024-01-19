package com.cryptospark.ui.models

import kotlinx.collections.immutable.ImmutableList

data class MarketDisplayable(
    val id: String,
    val symbol: String? = null,
    val name: String? = null,
    val image: String? = null,
    val currentPrice: Double? = null,
    val sparklineData: ImmutableList<com.cryptospark.data.model.DataPoint>
)