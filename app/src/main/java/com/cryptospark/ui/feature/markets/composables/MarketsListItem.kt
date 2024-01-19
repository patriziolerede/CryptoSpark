package com.cryptospark.ui.feature.markets.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cryptospark.R
import com.cryptospark.data.model.buildMarketPreview
import com.cryptospark.ui.feature.common.RoundedImage
import com.cryptospark.ui.feature.detail.composables.LineChart
import com.cryptospark.ui.models.MarketDisplayable

@Composable
fun MarketsListItem(
    market: MarketDisplayable,
    onItemClick: (MarketDisplayable) -> Unit
) {
    val paddingXXSmall = dimensionResource(id = R.dimen.padding_xxsmall)
    val paddingMedium = dimensionResource(id = R.dimen.padding_medium)
    val avatarSize = dimensionResource(id = R.dimen.avatar_size_medium)
    val dividerStartIndent = dimensionResource(id = R.dimen.user_list_item_start_indent)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick(market)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingMedium)
        ) {
            RoundedImage(
                url = market.image.orEmpty(),
                placeholder = R.drawable.avatar_placeholder,
                modifier = Modifier
                    .size(avatarSize)
                    .padding(end = paddingMedium)
            )
            Column {
                Text(
                    text = market.id,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.size(paddingXXSmall))


            }
        }
        Divider(
            startIndent = dividerStartIndent,
            modifier = Modifier.padding(end = paddingMedium)
        )

        LineChart(
            modifier = Modifier.size(width = 48.dp, height = 29.dp),
            data = market.sparklineData,
            graphColor = Color.Blue,
            showDashedLine = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MarketsListItemPreview() {
    MarketsListItem(market = buildMarketPreview(), onItemClick = {})
}