package com.cryptospark.ui.feature.markets.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cryptospark.ui.models.MarketDisplayable

@Composable
fun MarketsList(
    markets: List<MarketDisplayable>,
    onItemClick: (MarketDisplayable) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            item {
                MarketsListHeader()
            }
            items(markets) { market ->
                MarketsListItem(market = market, onItemClick = onItemClick)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UsersListPreview() {
    val markets = List(3) { com.cryptospark.data.model.buildMarketPreview() }
    MarketsList(markets = markets) {}
}