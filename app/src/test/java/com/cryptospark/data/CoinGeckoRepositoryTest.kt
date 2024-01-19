package com.cryptospark.data

import com.cryptospark.data.model.CoinDetail
import com.cryptospark.data.model.Description
import com.cryptospark.data.model.Image
import com.cryptospark.data.model.Links
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CoinGeckoRepositoryTest {

    private val coingeckoApi = mockk<CoingeckoApi>()
    private val coingeckoRepository: CoingeckoRepository = CryptoSparkRepositoryImpl(coingeckoApi)

    @Test
    fun `When getMarkets called then should call getMarkets from the API`() = runTest {
        // Given
        val markets = listOf(com.cryptospark.data.model.Market(""))
        coEvery { coingeckoApi.getMarkets() } returns markets

        // When
        val result = coingeckoRepository.getMarkets()

        // Then
        assert(result.isSuccess)
        coVerify(exactly = 1) { coingeckoApi.getMarkets() }
        confirmVerified(coingeckoApi)
    }

    @Test
    fun `When getDetail called then should call getCoinDetail from the API`() = runTest {
        // Given
        val coinId = "bitcoin"
        coEvery { coingeckoApi.getDetail(any()) } returns CoinDetail(
            "", "", "",
            Image("", "", ""), description = Description(""), links = Links(
                emptyList(), "",
                emptyList()
            ), sparklineIn7d = null
        )

        // When
        val result = coingeckoRepository.getCoinDetail(coinId)

        // Then
        assert(result.isSuccess)
        coVerify(exactly = 1) { coingeckoApi.getDetail(coinId) }
        confirmVerified(coingeckoApi)
    }


    @Test
    fun `Given an exception When getMarkets called then returns failure`() = runTest {
        // Given
        coEvery { coingeckoApi.getMarkets() } throws Exception("")

        // When
        val result = coingeckoRepository.getMarkets()

        // Then
        assert(result.isFailure)
    }

    @Test
    fun `Given an exception When getCoinDetail called then returns failure`() = runTest {
        // Given
        coEvery { coingeckoApi.getDetail(any()) } throws Exception("")

        // When
        val result = coingeckoRepository.getCoinDetail("bitcoin")

        // Then
        assert(result.isFailure)
    }


}