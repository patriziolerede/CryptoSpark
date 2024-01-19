package com.cryptospark.di

import com.cryptospark.ui.feature.detail.DetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.check.checkModules

class AppModuleTest {

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `verify koin app`() {
        koinApplication {
            modules(com.cryptospark.di.appModule, repositoryModule, viewModelModule)
            checkModules {
                withParameter<DetailViewModel> { "userId" }
            }
        }
    }
}