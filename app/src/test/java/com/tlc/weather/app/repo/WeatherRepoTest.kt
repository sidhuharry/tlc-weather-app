package com.tlc.weather.app.repo

import com.tlc.weather.app.model.City
import com.tlc.weather.app.model.Coordinates
import com.tlc.weather.app.model.DetailedWeather
import com.tlc.weather.app.model.NetworkResponse
import com.tlc.weather.app.network.service.WeatherApiService
import io.mockk.MockKAnnotations
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.anyLong
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class WeatherRepoTest {

    private lateinit var weatherRepo: WeatherRepo

    var apiService: WeatherApiService = mock<WeatherApiService>()

    var detailedWeather = DetailedWeather(
        City(900, "Melbourne", Coordinates(3.2, 4.5)),
        null,
        null,
        null,
        null,
        null,
        null,
        null
    )

    var cities = listOf(City(900, "Melbourne", Coordinates(3.2, 4.5)))

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        whenever(apiService.getCities()).thenReturn(
            flowOf(
                NetworkResponse.Success(
                    200,
                    cities
                )
            )
        )

        whenever(apiService.getDetailedWeather(anyLong())).thenReturn(
            flowOf(
                NetworkResponse.Success(
                    200,
                    DetailedWeather(
                        City(900, "Melbourne", Coordinates(3.2, 4.5)),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                    )
                )
            )
        )
        weatherRepo = WeatherRepo(apiService)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getCities() {
        runBlocking {
            var actualCities: List<City>? = null
            weatherRepo.getCities().collect {
                actualCities = (it as NetworkResponse.Success).responseBody as List<City>
            }
            Assert.assertEquals(cities, actualCities)
        }
    }

    @Test
    fun getDetailedWeather() {
        runBlocking {
            var actualDetailedWeather: DetailedWeather? = null
            weatherRepo.getDetailedWeather(900).collect {
                actualDetailedWeather =
                    (it as NetworkResponse.Success).responseBody as DetailedWeather
            }
            Assert.assertEquals(actualDetailedWeather, detailedWeather)
        }
    }

}