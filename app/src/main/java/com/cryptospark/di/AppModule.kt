package com.cryptospark.di

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        OkHttpClient.Builder()
            //.addInterceptor(ChuckerInterceptor( this.androidContext()))
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(com.cryptospark.data.Endpoints.BASE_URL)

            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single {
        get<Retrofit>().create(com.cryptospark.data.CoingeckoApi::class.java)
    }

}

val appModules = listOf(
    appModule,
    repositoryModule,
    viewModelModule
)
