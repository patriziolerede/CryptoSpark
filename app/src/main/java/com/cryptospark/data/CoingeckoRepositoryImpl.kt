package com.cryptospark.data

import com.cryptospark.ui.models.CoinDetailDisplayable
import com.cryptospark.ui.models.MarketDisplayable
import com.cryptospark.ui.toPresentationModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CryptoSparkRepositoryImpl(
    private val coingeckoApi: CoingeckoApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
): CoingeckoRepository {

    override suspend fun getMarkets(): Result<List<MarketDisplayable>> =
        makeApiCall(dispatcher) {
            coingeckoApi.getMarkets().map { it.toPresentationModel() }
        }

    override suspend fun getCoinDetail(coinId: String): Result<CoinDetailDisplayable?> =
        makeApiCall(dispatcher) {
            coingeckoApi.getDetail(coinId)?.toPresentationModel()
        }


}

suspend fun <T> makeApiCall(
    dispatcher: CoroutineDispatcher,
    call: suspend () -> T
): Result<T> = runCatching {
    withContext(dispatcher) {
        call.invoke()
    }
}
