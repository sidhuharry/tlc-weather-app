package com.tlc.weather.app.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.tlc.weather.app.model.*
import com.tlc.weather.app.repo.WeatherRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.spy
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class WeatherDetailsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    val dispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: WeatherDetailsViewModel

    @Mock
    lateinit var weatherRepo: WeatherRepo

    var dummyRespBody =
        this::class.java.getResource("/dummy_image.jpg")?.file?.toResponseBody("image/jpg".toMediaType())

    var detailedWeather = DetailedWeather(
        City(900, "Melbourne", Coordinates(3.2, 4.5)),
        null,
        null,
        null,
        null,
        null,
        listOf(WeatherData(icon = "rain.png")),
        null
    )

    @Mock
    lateinit var loaderObserver: Observer<Boolean>

    @Mock
    lateinit var errorObserver: Observer<Boolean>

    @Mock
    lateinit var weatherDetailedObserver: Observer<DetailedWeather>


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(dispatcher)
        viewModel = spy(WeatherDetailsViewModel(weatherRepo))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test viewModel loading cities`() = runBlocking {
        whenever(weatherRepo.getDetailedWeather(900)).thenReturn(
            flowOf(
                NetworkResponse.Success(
                    200,
                    detailedWeather
                )
            )
        )

        whenever(weatherRepo.getWeatherIcon(anyString())).thenReturn(
            flowOf(
                NetworkResponse.Success(
                    200,
                    dummyRespBody
                )
            )
        )
        viewModel.loadWeatherDetails(900)

        viewModel.weatherDetails.observeForever(weatherDetailedObserver)
        viewModel.isError.observeForever(errorObserver)
        viewModel.isLoading.observeForever(loaderObserver)

        verify(loaderObserver).onChanged(false)
        verify(errorObserver).onChanged(false)
        verify(weatherDetailedObserver).onChanged(detailedWeather)
    }

}