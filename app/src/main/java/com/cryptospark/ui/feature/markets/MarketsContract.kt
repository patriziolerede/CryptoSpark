package com.cryptospark.ui.feature.markets

import com.cryptospark.ui.base.ViewEvent
import com.cryptospark.ui.base.ViewSideEffect
import com.cryptospark.ui.base.ViewState
import com.cryptospark.ui.models.MarketDisplayable

class MarketsContract {

    sealed class Event : ViewEvent {
        object Retry : Event()
        data class MarketSelection(val market: MarketDisplayable) : Event()
    }

    data class State(
        val markets: List<MarketDisplayable>,
        val isLoading: Boolean,
        val isError: Boolean,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        object DataWasLoaded : Effect()

        sealed class Navigation : Effect() {
            data class ToRepos(val coinId: String): Navigation()
        }
    }

}
