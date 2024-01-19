package com.cryptospark.ui.feature.detail

import com.cryptospark.ui.base.ViewEvent
import com.cryptospark.ui.base.ViewSideEffect
import com.cryptospark.ui.base.ViewState
import com.cryptospark.ui.models.CoinDetailDisplayable

class DetailContract {

    sealed class Event : ViewEvent {
        object Retry : Event()
        object BackButtonClicked : Event()
    }

    data class State(
        val detail: CoinDetailDisplayable?,
        val isLoading: Boolean,
        val isError: Boolean,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            object Back : Navigation()
        }
    }

}
