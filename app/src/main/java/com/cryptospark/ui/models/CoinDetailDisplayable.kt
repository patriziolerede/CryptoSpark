package com.cryptospark.ui.models

import kotlinx.collections.immutable.ImmutableList

data class CoinDetailDisplayable(
    val id: String,
    val symbol: String? = null,
    val name: String,
    val image: String? = null,
    val description: String,
    val website: String? = null,
    val sparklineData: ImmutableList<com.cryptospark.data.model.DataPoint>
)