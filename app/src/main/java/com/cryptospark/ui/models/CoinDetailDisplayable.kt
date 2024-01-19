package com.cryptospark.ui.models

import android.text.Spanned
import kotlinx.collections.immutable.ImmutableList

data class CoinDetailDisplayable(
    val id: String,
    val symbol: String? = null,
    val name: String,
    val image: String? = null,
    val description: Spanned,
    val website: String? = null,
    val sparklineData: ImmutableList<DataPoint>
)