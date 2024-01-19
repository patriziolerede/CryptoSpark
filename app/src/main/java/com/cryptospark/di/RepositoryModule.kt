package com.cryptospark.di

import com.cryptospark.data.CoingeckoRepository
import com.cryptospark.data.CryptoSparkRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    factory<CoingeckoRepository> {
        CryptoSparkRepositoryImpl(
            coingeckoApi = get()
        )
    }

}