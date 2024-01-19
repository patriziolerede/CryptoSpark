package com.cryptospark.ui.feature.detail.composables

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cryptospark.R

@Composable
fun DetailTopBar(coinName:String,
                 onBackButtonClicked: () -> Unit) {
    TopAppBar(
        title = { Text(text = coinName) },
        navigationIcon = {
            IconButton(onClick = { onBackButtonClicked() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DetailTopBarPreview() {
    DetailTopBar("bitcoin") {}
}