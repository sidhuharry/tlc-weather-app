package com.tlc.weather.app

import com.tlc.weather.app.di.appModule
import com.tlc.weather.app.di.networkModule
import com.tlc.weather.app.di.repoModule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.check.checkKoinModules

class KoinDiTest : KoinTest {

    @Test
    fun verifyKoinApp() {

        checkKoinModules(networkModule, appModule, repoModule)
    }
}