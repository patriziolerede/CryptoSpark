package com.cryptospark.ui.feature.markets

import androidx.lifecycle.viewModelScope
import com.cryptospark.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class MarketsViewModel(
    private val coinGeckoRepository: com.cryptospark.data.CoingeckoRepository
) : BaseViewModel<MarketsContract.Event, MarketsContract.State, MarketsContract.Effect>() {

    init { getMarkets() }

    override fun setInitialState() = MarketsContract.State(
        markets = emptyList(),
        isLoading = true,
        isError = false,
    )

    override fun handleEvents(event: MarketsContract.Event) {
        when (event) {
            is MarketsContract.Event.MarketSelection -> setEffect { MarketsContract.Effect.Navigation.ToRepos(event.market.id) }
            is MarketsContract.Event.Retry -> getMarkets()
        }
    }

    fun getMarkets() {
        viewModelScope.launch {
            setState { copy(isLoading = true, isError = false) }

            coinGeckoRepository.getMarkets()
                .onSuccess { markets ->
                    setState { copy(markets = markets, isLoading = false) }
                    setEffect { MarketsContract.Effect.DataWasLoaded }
                }
                .onFailure {
                    setState { copy(isError = true, isLoading = false) }
                }
        }
    }
}
