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
    )

    override fun handleEvents(event: DetailContract.Event) {
        when (event) {
            DetailContract.Event.BackButtonClicked -> {
                setEffect { DetailContract.Effect.Navigation.Back }
            }
            DetailContract.Event.Retry -> getAll()
        }
    }

    private fun getAll() {
        viewModelScope.launch {
            getUser()
        }
    }

    private suspend fun getUser() {
        coingeckoRepository.getCoinDetail(coinId)
            .onSuccess { coinDetail ->
                setState { copy(detail = coinDetail, isLoading = false) }
            }
            .onFailure {
                setState { copy(isError = true, isLoading = false) }
            }
    }


}