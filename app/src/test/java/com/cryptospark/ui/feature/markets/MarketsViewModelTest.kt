package com.cryptospark.ui.feature.markets

import androidx.compose.runtime.snapshots.Snapshot
import com.cryptospark.MainCoroutineRule
import com.cryptospark.ui.models.MarketDisplayable
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@DelicateCoroutinesApi
@OptIn(ExperimentalCoroutinesApi::class)
class MarketsViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val coingeckoRepository = mockk<com.cryptospark.data.CoingeckoRepository>()

    @Test
    fun `When view model initialized then should emit initial view state first`() = runTest {
        // Given
        val expectedInitialViewState = MarketsContract.State(
            markets = emptyList(),
            isLoading = true,
            isError = false
        )

        // When
        val viewModel = MarketsViewModel(coingeckoRepository)

        // Then
        assertEquals(expectedInitialViewState, viewModel.viewState.value)
    }

    @Test
    fun `When getMarkets called then should emit a view state`() = runTest {
        // Given
        val markets = listOf(MarketDisplayable(id = "bitcoin", sparklineData = persistentListOf()))
        val expectedViewState = MarketsContract.State(
            markets = markets,
            isLoading = false,
            isError = false
        )
        coEvery { coingeckoRepository.getMarkets() } returns Result.success(markets)

        // When
        val viewModel = MarketsViewModel(coingeckoRepository)

        // Then
        assertEquals(expectedViewState, viewModel.viewState.value)
    }

    @Test
    fun `When `() = runTest {

        // Given
        val markets = listOf(MarketDisplayable(id = "bitcoin", sparklineData = persistentListOf()))
        val expectedViewState = MarketsContract.State(
            markets = markets,
            isLoading = false,
            isError = false
        )
        coEvery { coingeckoRepository.getMarkets() } returns Result.success(markets)

        // When
        val snapshot = Snapshot.takeMutableSnapshot(
//            readObserver = {
//                println(it)
//            },
            writeObserver = {
                println(it)
            }
        )

        snapshot.enter {
            val viewModel = MarketsViewModel(coingeckoRepository)

            // Then
            assertEquals(expectedViewState, viewModel.viewState.value)
        }

        snapshot.apply()
    }

}