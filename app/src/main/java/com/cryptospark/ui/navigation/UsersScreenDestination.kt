package com.cryptospark.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.cryptospark.ui.feature.markets.MarketsContract
import com.cryptospark.ui.feature.markets.MarketsViewModel
import com.cryptospark.ui.feature.markets.composables.MarketsScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun MarketsScreenDestination(navController: NavController) {
    val viewModel = getViewModel<MarketsViewModel>()
    MarketsScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event ->  viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is MarketsContract.Effect.Navigation.ToRepos) {
                navController.navigateToRepos(navigationEffect.coinId)
            }
        }
    )
}
