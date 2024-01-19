package com.cryptospark.ui.feature.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.cryptospark.R

@Composable
fun RoundedImage(
    url: String,
    modifier: Modifier = Modifier,
    crossfade: Boolean = true,
) {
    Image(
        painter = rememberImagePainter(
            data = url,
            builder = {
                crossfade(crossfade)
                transformations(CircleCropTransformation())
            }
        ),
        contentDescription = null,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun RoundedImagePreview() {
    RoundedImage(
        url = "https://assets.coingecko.com/coins/images/325/large/Tether.png?1696501661",
        modifier = Modifier.size(dimensionResource(id = R.dimen.avatar_size_medium))
    )
}