package com.cryptospark.ui.feature.detail.composables

import android.text.util.Linkify.WEB_URLS
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Link
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.cryptospark.R
import com.cryptospark.ui.feature.common.RoundedImage
import com.cryptospark.ui.models.CoinDetailDisplayable
import com.cryptospark.ui.theme.backgroundColor
import com.cryptospark.ui.theme.paddingXL
import com.cryptospark.ui.theme.primaryColor
import com.google.android.material.textview.MaterialTextView
import java.util.Locale

@Composable
fun DetailHeader(
    coinDetail: CoinDetailDisplayable,
    isChartVisible: Boolean,
    onShowChart: () -> Unit
) {
    val paddingMedium = dimensionResource(id = R.dimen.padding_medium)
    Scaffold(
        floatingActionButton = {
            ButtonSection(coinDetail)
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(backgroundColor),
                contentAlignment = Alignment.TopCenter
            ) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp))
                        .background(backgroundColor)
                        .padding(16.dp),
                    contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                ) {
                    item {
                        IconSection(coinDetail)

                        Spacer(modifier = Modifier.size(paddingMedium))
                    }
                    item {

                        AnimatedVisibility(
                            visible = isChartVisible,
                            enter = expandVertically() + fadeIn(),
                            exit = ExitTransition.None
                        ) {

                            LineChart(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(128.dp),
                                data = coinDetail.sparklineData,
                                graphColor = primaryColor,
                                showDashedLine = true,
                                showYLabels = true
                            )
                        }

                        LaunchedEffect(null) {
                            onShowChart()
                        }
                        Spacer(modifier = Modifier.size(paddingMedium))
                        DetailSession(coinDetail)

                        Spacer(modifier = Modifier.size(paddingMedium))

                    }

                }

            }
        }
    )

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
        style = MaterialTheme.typography.h2,
        fontWeight = FontWeight.Bold,
        color = primaryColor
    )

    Spacer(modifier = Modifier.size(paddingXL))
    Text(
        text = coinDetail.symbol.orEmpty(),
        fontWeight = FontWeight.SemiBold,
        color = primaryColor,
        textAlign = TextAlign.Start
    )
    Spacer(modifier = Modifier.size(paddingXL))
    AndroidView(
        modifier = Modifier.fillMaxWidth(),
        factory = {
            MaterialTextView(it).apply {
                // links
                autoLinkMask = WEB_URLS
                linksClickable = true
                // setting the color to use forr highlihting the links
                setLinkTextColor(primaryColor.toArgb())
            }
        },
        update = {
            it.text = coinDetail.description
        }
    )
}

@Composable
fun ButtonSection(coinDetail: CoinDetailDisplayable) {

    val context = LocalContext.current
    val websiteIntent =
        remember { coinDetail.website?.let { com.cryptospark.common.buildUrlIntent(it) } }

    // View Website
    val websiteNotFoundDialog = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), horizontalArrangement = Arrangement.End
    ) {


        OutlinedButton(modifier = Modifier.size(48.dp),
            shape = CircleShape,
            border = BorderStroke(1.dp, primaryColor),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = primaryColor), onClick = {
                if (coinDetail.website.isNullOrEmpty()) {
                    websiteNotFoundDialog.value = true
                } else {
                    context.startActivity(websiteIntent)
                }
            }) {
            Icon(
                imageVector = Icons.Rounded.Link,
                contentDescription = stringResource(R.string.button_view_website_title),
                tint = primaryColor
            )
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
                Text(
                    text = stringResource(R.string.website_not_found_dialog_confirm_button).uppercase(
                        Locale.getDefault()
                    )
                )
            })
    }
}