package com.tlc.weather.app.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.tlc.weather.app.model.CitiesResponse
import com.tlc.weather.app.model.City
import com.tlc.weather.app.model.Coordinates
import com.tlc.weather.app.model.NetworkResponse
import com.tlc.weather.app.repo.WeatherRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.spy
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class HomeActivityViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    val dispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: HomeActivityViewModel

    @Mock
    lateinit var weatherRepo: WeatherRepo

    var cities = listOf(City(900, "Melbourne", Coordinates(3.2, 4.5)))

    @Mock
    lateinit var loaderObserver: Observer<Boolean>

    @Mock
    lateinit var errorObserver: Observer<Boolean>

    @Mock
    lateinit var cityObserver: Observer<CitiesResponse>


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(dispatcher)
        viewModel = spy(HomeActivityViewModel(weatherRepo))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test viewModel loading cities`() = runBlocking {
        whenever(weatherRepo.getCities()).thenReturn(
            flowOf(
                NetworkResponse.Success(
                    200,
                    CitiesResponse(1, cities)
                )
            )
        )
        viewModel.loadCities()

        viewModel.cities.observeForever(cityObserver)
        viewModel.isError.observeForever(errorObserver)
        viewModel.isLoading.observeForever(loaderObserver)

        assertEquals(1, viewModel.cities.value?.cityList?.count())

        verify(loaderObserver).onChanged(false)
        verify(errorObserver).onChanged(false)
        verify(cityObserver).onChanged(CitiesResponse(1, cityList = cities))
    }

}