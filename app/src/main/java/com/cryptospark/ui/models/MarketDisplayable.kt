package com.cryptospark.ui.models

import kotlinx.collections.immutable.ImmutableList

data class MarketDisplayable(
    val id: String,
    val symbol: String? = null,
    val name: String? = null,
    val image: String? = null,
    val currentPrice: String,
    val sparklineData: ImmutableList<DataPoint>
)