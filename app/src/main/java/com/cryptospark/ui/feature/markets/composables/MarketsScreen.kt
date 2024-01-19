package com.cryptospark.ui.feature.markets.composables

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cryptospark.R
import com.cryptospark.ui.base.SIDE_EFFECTS_KEY
import com.cryptospark.ui.feature.common.NetworkError
import com.cryptospark.ui.feature.common.Progress
import com.cryptospark.ui.feature.markets.MarketsContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun MarketsScreen(
    state: MarketsContract.State,
    effectFlow: Flow<MarketsContract.Effect>?,
    onEventSent: (event: MarketsContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: MarketsContract.Effect.Navigation) -> Unit
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val snackBarMessage = stringResource(R.string.markets_screen_snackbar_loaded_message)

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is MarketsContract.Effect.DataWasLoaded -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = snackBarMessage,
                        duration = SnackbarDuration.Short
                    )
                }
                is MarketsContract.Effect.Navigation.ToRepos -> onNavigationRequested(effect)
            }
        }?.collect()
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { MarketsTopBar() }
    ) {
        when {
            state.isLoading -> Progress()
            state.isError -> NetworkError { onEventSent(MarketsContract.Event.Retry) }
            else -> MarketsList(markets = state.markets) { onEventSent(MarketsContract.Event.UserSelection(it)) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MarketsScreenSuccessPreview() {
    val markets = List(3) { com.cryptospark.data.model.buildMarketPreview() }
    MarketsScreen(
        state = MarketsContract.State(
            markets = markets,
            isLoading = false,
            isError = false,
        ),
        effectFlow = null,
        onEventSent = {},
        onNavigationRequested = {},
    )
}

@Preview(showBackground = true)
@Composable
fun MarketsScreenErrorPreview() {
    MarketsScreen(
        state = MarketsContract.State(
            markets = emptyList(),
            isLoading = false,
            isError = true,
        ),
        effectFlow = null,
        onEventSent = {},
        onNavigationRequested = {},
    )
}
