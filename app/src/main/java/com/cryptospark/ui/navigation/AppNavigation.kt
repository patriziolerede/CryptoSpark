package com.cryptospark.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cryptospark.ui.navigation.Navigation.Args.COIN_ID

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Navigation.Routes.MARKETS
    ) {
        composable(
            route = Navigation.Routes.MARKETS
        ) {
            MarketsScreenDestination(navController)
        }

        composable(
            route = Navigation.Routes.REPOS,
            arguments = listOf(navArgument(name = COIN_ID) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val coinId = requireNotNull(backStackEntry.arguments?.getString(COIN_ID)) { "Coin id is required as an argument" }
            ReposScreenDestination(
                CoinId = coinId,
                navController = navController
            )
        }
    }
}

object Navigation {

    object Args {
        const val COIN_ID = "coin_id"
    }

    object Routes {
        const val MARKETS = "markets"
        const val REPOS = "$MARKETS/{$COIN_ID}"
    }

}

fun NavController.navigateToRepos(coinId: String) {
    navigate(route = "${Navigation.Routes.MARKETS}/$coinId")
}
