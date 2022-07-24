package com.tlc.weather.app.ui.home

import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tlc.weather.app.di.appModule
import com.tlc.weather.app.di.networkModule
import com.tlc.weather.app.di.repoModule
import io.mockk.justRun
import io.mockk.mockkClass
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.KoinTestRule
import org.koin.test.mock.MockProviderRule
import kotlin.test.assertNotNull

@RunWith(AndroidJUnit4::class)
class HomeActivityTest : AutoCloseKoinTest() {

    @get:Rule
    @JvmField
    val koinTestRule = KoinTestRule.create {
        modules(networkModule, repoModule, appModule)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        mockkClass(clazz)
    }

    lateinit var homeActivityViewModel: HomeActivityViewModel


    @Before
    fun setUp() {
        homeActivityViewModel = mockkClass(HomeActivityViewModel::class)
        loadKoinModules(listOf(networkModule, repoModule, module { homeActivityViewModel }))
    }

    @After
    fun tearDown() {
    }

    @Test
    fun checkInjection() {
        justRun { homeActivityViewModel.loadCities() }
        launchActivity<HomeActivity>().use {
            assertNotNull(homeActivityViewModel)
        }
    }
}