package com.cryptospark.ui.feature.markets.composables

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cryptospark.R

@Composable
fun MarketsTopBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.markets_screen_top_bar_title)) }
    )
}

@Preview(showBackground = true)
@Composable
fun ReposTopBarPreview() {
    MarketsTopBar()
}