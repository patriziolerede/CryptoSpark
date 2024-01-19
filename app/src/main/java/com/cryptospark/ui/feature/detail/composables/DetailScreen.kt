package com.cryptospark.ui.feature.detail.composables

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import com.cryptospark.ui.base.SIDE_EFFECTS_KEY
import com.cryptospark.ui.feature.common.NetworkError
import com.cryptospark.ui.feature.common.Progress
import com.cryptospark.ui.feature.detail.DetailContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    state: DetailContract.State,
    effectFlow: Flow<DetailContract.Effect>?,
    onEventSent: (event: DetailContract.Event) -> Unit,
    onNavigationRequested: (DetailContract.Effect.Navigation) -> Unit
) {

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                DetailContract.Effect.Navigation.Back -> {
                    onNavigationRequested(DetailContract.Effect.Navigation.Back)
                }
            }
        }?.collect()
    }

    Scaffold(
        topBar = {
            DetailTopBar(state.detail?.name.orEmpty()) {
                onEventSent(DetailContract.Event.BackButtonClicked)
            }
        }
    ) {
        when {
            state.isLoading -> Progress()
            state.isError -> NetworkError { onEventSent(DetailContract.Event.Retry) }
            else -> {
                state.detail?.let { detail ->
                    DetailHeader(coinDetail = detail)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailScreenErrorPreview() {
    DetailScreen(
        state = DetailContract.State(
            detail = com.cryptospark.data.model.buildCoinDetailPreview(),
            isLoading = false,
            isError = true,
        ),
        effectFlow = null,
        onEventSent = {},
        onNavigationRequested = {},
    )
}
