package com.cryptospark.ui

import androidx.compose.ui.graphics.Color
import androidx.core.text.HtmlCompat
import com.cryptospark.ui.models.CoinDetailDisplayable
import com.cryptospark.ui.models.DataPoint
import com.cryptospark.ui.models.MarketDisplayable
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.Currency



fun com.cryptospark.data.model.Market.toPresentationModel() = MarketDisplayable(
    id = id,
    name = name,
   symbol = symbol,
    image = image,
    currentPrice = currentPrice?.roundToTwoDecimals()?.addSuffix("€").orNA(),
    sparklineData = sparklineIn7d?.price.toSparklineData()
)

fun com.cryptospark.data.model.CoinDetail.toPresentationModel() = CoinDetailDisplayable(
    id = id,
    name = name,
    symbol = symbol,
    image = image.large,
    website = links.homepage.first(),
    description = HtmlCompat.fromHtml(description.en, 0),
    sparklineData = marketData?.sparkline7d?.price.toSparklineData()
)


private val formatTwo = java.text.DecimalFormat("##.##")
fun String.addSuffix(value: String) = value + this
fun Double.roundToTwoDecimals() = formatTwo.format(this).toString()


fun List<Double>?.toSparklineData() =
    this?.let {
        val div = when(it.size) {
            in 0..100 -> 5
            in 100..200 -> 10
            else -> 20
        }

        it.filterIndexed { index, _ ->
            index % div == 0
        }

    }?.map {
        it.roundToNDecimals(decimals = 8)
    }?.mapIndexed { _, d ->
        DataPoint(y = d, xLabel = null, yLabel = null)
    }?.toImmutableList() ?: persistentListOf()

fun Double.roundToNDecimals(decimals: Int, roundingMode: RoundingMode = RoundingMode.UP): Double {
    return this.toBigDecimal().setScale(decimals, roundingMode).toDouble()
}
private fun Double?.toCurrency():String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 0
    format.currency = Currency.getInstance("EUR")

   return format.format(this)
}

    private fun String?.orNotAvailable(): String {
        return this ?: NOT_AVAILABLE
    }

    private fun String?.orNA(): String {
        return this ?: NA
    }

    private fun Color?.orNeutral(): Color {
        return this ?: Color.Gray
    }



        const val NOT_AVAILABLE = "Not available"
        const val NA = "N.A."


