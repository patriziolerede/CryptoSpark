package com.cryptospark.di

import com.cryptospark.ui.feature.detail.DetailViewModel
import com.cryptospark.ui.feature.markets.MarketsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MarketsViewModel(coinGeckoRepository = get())
    }

    viewModel { parameters ->
        DetailViewModel(
            coinId = parameters.get(),
            coingeckoRepository = get()
        )
    }
}
