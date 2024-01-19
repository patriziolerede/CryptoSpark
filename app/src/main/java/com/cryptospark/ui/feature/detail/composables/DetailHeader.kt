package com.cryptospark.ui.feature.detail.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cryptospark.R
import com.cryptospark.ui.feature.common.RoundedImage
import com.cryptospark.ui.models.CoinDetailDisplayable
import com.cryptospark.ui.theme.OnSurfaceTextAlpha
import com.cryptospark.ui.theme.Purple200
import java.util.Locale

@Composable
fun DetailHeader(coinDetail: CoinDetailDisplayable) {
    val paddingMedium = dimensionResource(id = R.dimen.padding_medium)
    val paddingXSmall = dimensionResource(id = R.dimen.padding_xsmall)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        item {
            IconSection(coinDetail)

            Spacer(modifier = Modifier.size(paddingXSmall))
        }
        item {
            AnimatedVisibility(
                visible = true,
                exit = ExitTransition.None
            ) {

                LineChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    data = coinDetail.sparklineData,
                    graphColor = Purple200,
                    showDashedLine = true,
                    showYLabels = true
                )
            }

            DetailSession(coinDetail)

            Spacer(modifier = Modifier.size(paddingMedium))

            ButtonsSession(coinDetail)
        }

    }
}

@Composable
fun IconSection(coinDetail: CoinDetailDisplayable) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        RoundedImage(
            url = coinDetail.image.orEmpty(),
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.avatar_size_large))
                .padding(end = dimensionResource(id = R.dimen.padding_medium))
        )

    }
}

@Composable
fun DetailSession(coinDetail: CoinDetailDisplayable) {
    Text(
        text = coinDetail.name,
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold
    )
    Text(
        text = coinDetail.symbol.orEmpty(),
        style = MaterialTheme.typography.h3,
        textAlign = TextAlign.Start
    )
    Text(
        text = coinDetail.description,
        style = MaterialTheme.typography.subtitle1,
        color = MaterialTheme.colors.onSurface.copy(alpha = OnSurfaceTextAlpha)
    )
}

@Composable
fun ButtonsSession(coinDetail: CoinDetailDisplayable) {
    // See all button
    val context = LocalContext.current
    val websiteIntent = remember{ com.cryptospark.common.buildUrlIntent("") }

    // View Website
    val websiteNotFoundDialog = remember { mutableStateOf(false) }

    Row(modifier = Modifier.fillMaxWidth()) {
   
        OutlinedButton(onClick = {
            if (coinDetail.id.isEmpty()) {
                websiteNotFoundDialog.value = true
            } else {
                context.startActivity(websiteIntent)
            }
        }) {
            Text(text = stringResource(R.string.button_view_website_title))
        }
    }

    if (websiteNotFoundDialog.value) {
        AlertDialog(onDismissRequest = {
            websiteNotFoundDialog.value = false
        },
            title = {
                Text(text = stringResource(R.string.website_not_found_dialog_title))
            },
            text = {
                Text(text = stringResource(R.string.website_not_found_dialog_text))
            },
            confirmButton = {
                Text(text = stringResource(R.string.website_not_found_dialog_confirm_button).uppercase(
                    Locale.getDefault()
                ))
            })
    }
}