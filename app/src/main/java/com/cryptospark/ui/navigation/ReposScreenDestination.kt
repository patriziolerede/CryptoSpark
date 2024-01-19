package com.cryptospark.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.cryptospark.ui.feature.detail.DetailContract
import com.cryptospark.ui.feature.detail.DetailViewModel
import com.cryptospark.ui.feature.detail.composables.DetailScreen
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ReposScreenDestination(CoinId: String, navController: NavController) {
    val viewModel = getViewModel<DetailViewModel> { parametersOf(CoinId) }
    DetailScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is DetailContract.Effect.Navigation.Back) {
                navController.popBackStack()
            }
        },
    )
}
