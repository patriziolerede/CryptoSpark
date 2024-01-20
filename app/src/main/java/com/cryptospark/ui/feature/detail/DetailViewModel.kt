package com.cryptospark.ui.feature.detail

import androidx.lifecycle.viewModelScope
import com.cryptospark.data.CoingeckoRepository
import com.cryptospark.ui.base.BaseViewModel
import com.cryptospark.ui.toPresentationModel
import kotlinx.coroutines.launch

class DetailViewModel(
    private val coinId: String,
    private val coingeckoRepository: CoingeckoRepository
) : BaseViewModel<DetailContract.Event, DetailContract.State, DetailContract.Effect>() {

    init { getAll() }

    override fun setInitialState() = DetailContract.State(
        detail = null,
        isLoading = true,
        isError = false,
        isChartVisible = false
    )

    override fun handleEvents(event: DetailContract.Event) {
        when (event) {
            DetailContract.Event.BackButtonClicked -> {
                setEffect { DetailContract.Effect.Navigation.Back }
            }
            DetailContract.Event.Retry -> getAll()
            DetailContract.Event.ShowCart -> showChart()
        }
    }

    fun showChart() {
        setState { copy(isChartVisible = true) }
    }

    private fun getAll() {
        viewModelScope.launch {
            getDetail()
        }
    }

    private suspend fun getDetail() {
        coingeckoRepository.getCoinDetail(coinId)
            .onSuccess { coinDetail ->
                setState { copy(detail = coinDetail, isLoading = false) }
            }
            .onFailure {
                setState { copy(isError = true, isLoading = false) }
            }
    }


}