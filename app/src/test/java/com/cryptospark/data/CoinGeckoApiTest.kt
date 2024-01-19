package com.cryptospark.data

import com.cryptospark.data.model.CoinDetail
import com.cryptospark.data.model.Description
import com.cryptospark.data.model.Image
import com.cryptospark.data.model.Links
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals

class CoinGeckoApiTest {

    private val mockWebService = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(mockWebService.url("/"))
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(CoingeckoApi::class.java)

    @After
    fun tearDown() {
        mockWebService.shutdown()
    }

    @Test
    fun `Given 200 response When fetching markets Then returns markets correctly`() {
        // Given
        mockWebService.enqueueResponse(
            fileName = "markets.json",
            code = 200
        )
        val expected = listOf(
            com.cryptospark.data.model.Market(
                id = "bitcoin",
                image = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1696501400"
            ),
            com.cryptospark.data.model.Market(
                id = "ethereum",
                image = "https://assets.coingecko.com/coins/images/279/large/ethereum.png?1696501628"
            ),
            com.cryptospark.data.model.Market(
                id = "tether",
                image = "https://assets.coingecko.com/coins/images/325/large/Tether.png?1696501661"
            ),
        )

        // When
        val actual = runBlocking { api.getMarkets() }
        val request = mockWebService.takeRequest()

        // Then
        assertEquals(expected, actual)
        assertEquals("/markets", request.path)
    }

    @Test
    fun `Given 200 response When fetching user Then returns coindetail correctly`() {
        // Given
        val coinId = "bitcoin"
        mockWebService.enqueueResponse(
            fileName = "coin-detail.json",
            code = 200
        )


        val expected = CoinDetail(
            id = "bitcoin",
            symbol = "btc",
            description = Description(
                "Bitcoin is the first successful internet money based on peer-to-peer technology; whereby no central bank or authority is involved in the transaction and production of the Bitcoin currency. It was created by an anonymous individual/group under the name, Satoshi Nakamoto. The source code is available publicly as an open source project, anybody can look at it and be part of the developmental process.\r\n\r\nBitcoin is changing the way we see money as we speak. The idea was to produce a means of exchange, independent of any central authority, that could be transferred electronically in a secure, verifiable and immutable way. It is a decentralized peer-to-peer internet currency making mobile payment easy, very low transaction fees, protects your identity, and it works anywhere all the time with no central authority and banks.\r\n\r\nBitcoin is designed to have only 21 million BTC ever created, thus making it a deflationary currency. Bitcoin uses the <a href=\"https://www.coingecko.com/en?hashing_algorithm=SHA-256\">SHA-256</a> hashing algorithm with an average transaction confirmation time of 10 minutes. Miners today are mining Bitcoin using ASIC chip dedicated to only mining Bitcoin, and the hash rate has shot up to peta hashes.\r\n\r\nBeing the first successful online cryptography currency, Bitcoin has inspired other alternative currencies such as <a href=\"https://www.coingecko.com/en/coins/litecoin\">Litecoin</a>, <a href=\"https://www.coingecko.com/en/coins/peercoin\">Peercoin</a>, <a href=\"https://www.coingecko.com/en/coins/primecoin\">Primecoin</a>, and so on.\r\n\r\nThe cryptocurrency then took off with the innovation of the turing-complete smart contract by <a href=\"https://www.coingecko.com/en/coins/ethereum\">Ethereum</a> which led to the development of other amazing projects such as <a href=\"https://www.coingecko.com/en/coins/eos\">EOS</a>, <a href=\"https://www.coingecko.com/en/coins/tron\">Tron</a>, and even crypto-collectibles such as <a href=\"https://www.coingecko.com/buzz/ethereum-still-king-dapps-cryptokitties-need-1-billion-on-eos\">CryptoKitties</a>."
            ),
            links = Links(listOf("http://www.bitcoin.org"), "", emptyList()),
            image = Image(
                "https://assets.coingecko.com/coins/images/1/thumb/bitcoin.png?1696501400",
                "https://assets.coingecko.com/coins/images/1/small/bitcoin.png?1696501400",
                "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1696501400"
            ),

            name = "Bitcoin"
        )

        // When
        val actual = runBlocking { api.getDetail(coinId) }
        val request = mockWebService.takeRequest()

        // Then
        assertEquals(expected, actual)
        assertEquals("/markets/$coinId", request.path)
    }

}