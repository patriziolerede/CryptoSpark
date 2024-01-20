package com.cryptospark.ui.feature.markets.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cryptospark.R
import com.cryptospark.data.model.buildMarketPreview
import com.cryptospark.ui.feature.common.RoundedImage
import com.cryptospark.ui.feature.common.coloredShadow
import com.cryptospark.ui.feature.detail.composables.LineChart
import com.cryptospark.ui.models.MarketDisplayable
import com.cryptospark.ui.theme.Purple200
import com.cryptospark.ui.theme.Purple500
import com.cryptospark.ui.theme.SuccessColor
import com.cryptospark.ui.theme.backgroundColor
import com.cryptospark.ui.theme.paddingNone
import com.cryptospark.ui.theme.paddingSmall
import com.cryptospark.ui.theme.paddingXL
import com.cryptospark.ui.theme.paddingXXL
import com.cryptospark.ui.theme.primaryColor
import com.cryptospark.ui.theme.primaryDarkColor

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MarketsListItem(
    market: MarketDisplayable,
    onItemClick: (MarketDisplayable) -> Unit
) {
   val shapeXlarge = dimensionResource(id = R.dimen.shape_xlarge)
    val paddingMedium = dimensionResource(id = R.dimen.padding_medium)
    val imageSize = dimensionResource(id = R.dimen.avatar_size_medium)
    val gradientBrush = Brush.verticalGradient(listOf(backgroundColor.copy(.3F), backgroundColor


        .copy(.5F)), startY = 10F)

        Row(
            modifier = Modifier

                .fillMaxWidth()
                .padding(paddingXL)
                .coloredShadow(
                    backgroundColor,
                    alpha = 0.7F,
                    borderRadius = paddingXXL,
                    shadowRadius = paddingMedium,
                    offsetX = paddingNone,
                    offsetY = paddingSmall
                )
                .clip(RoundedCornerShape(shapeXlarge))
                .background(brush = gradientBrush)

                .clickable(onClick = {
                    onItemClick(market)
                }),

            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            RoundedImage(

                url = market.image.orEmpty(),
                modifier = Modifier
                    .size(imageSize)
                    .padding(paddingMedium)
            )

            Column(
                modifier = Modifier.weight(0.4f)
                    .padding(top = paddingMedium, bottom = paddingMedium)
            ) {
                Text(
                    text = market.name.orEmpty(),
                    style = MaterialTheme.typography.h2,
                    fontWeight = FontWeight.Bold,
                    color = primaryColor,
                    modifier = Modifier.width(IntrinsicSize.Max)
                )
                Text(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    text = market.currentPrice,
                    textAlign = TextAlign.Center,
                    color = primaryColor,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1
                )
            }

            LineChart(
                modifier = Modifier.size(width = 100.dp, height = 50.dp)
                    .padding(paddingMedium)
                    .align(Alignment.CenterVertically),
                data = market.sparklineData,
                graphColor =   primaryColor,
                showDashedLine = true
            )

        }

}

@Preview(showBackground = true)
@Composable
fun MarketsListItemPreview() {
    MarketsListItem(market = buildMarketPreview(), onItemClick = {})
}