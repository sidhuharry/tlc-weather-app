package com.tlc.weather.app.network.service

import com.tlc.weather.app.TestUtils
import com.tlc.weather.app.model.CitiesResponse
import com.tlc.weather.app.model.DetailedWeather
import com.tlc.weather.app.model.NetworkResponse
import com.tlc.weather.app.network.api.OpenWeatherApi
import com.tlc.weather.app.network.api.WeatherApi
import com.tlc.weather.app.network.util.NetworkUtils
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class WeatherApiServiceTest {

    private val mockWebServer = MockWebServer()

    private lateinit var weatherApiService: WeatherApiService

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val mockWeatherApi = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)

    private val mockOpenWeatherApi = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(OpenWeatherApi::class.java)


    @Before
    fun setUp() {
        weatherApiService = WeatherApiService(mockWeatherApi, NetworkUtils(), mockOpenWeatherApi)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getCities() happy path`() {
        runBlocking {
            val mockResponse =
                MockResponse().addHeader("Content-Type", "application/json; charset=utf-8")
                    .addHeader("Cache-Control", "no-cache").setBody(
                        TestUtils.cityListJsonResp
                    ).setResponseCode(200)
            mockWebServer.enqueue(mockResponse)
            val actualCitiesName: MutableList<String> = mutableListOf()
            weatherApiService.getCities().map {
                val resp = ((it as NetworkResponse.Success).responseBody) as CitiesResponse
                resp.cityList.map { city -> city.name }
            }.collect {
                actualCitiesName.addAll(it)
            }
            Assert.assertEquals(TestUtils.expectedCityNames, actualCitiesName)
        }
    }


    @Test
    fun `getCities() with 200 but some city names are empty`() {
        runBlocking {
            val mockResponse =
                MockResponse().addHeader("Content-Type", "application/json; charset=utf-8")
                    .addHeader("Cache-Control", "no-cache").setBody(
                        TestUtils.cityListJsonRespWithEmptyCityNames
                    ).setResponseCode(200)
            mockWebServer.enqueue(mockResponse)
            val actualCitiesName: MutableList<String> = mutableListOf()
            weatherApiService.getCities().map {
                val resp = ((it as NetworkResponse.Success).responseBody) as CitiesResponse
                resp.cityList.map { city -> city.name }
            }.collect {
                actualCitiesName.addAll(it)
            }
            Assert.assertEquals(TestUtils.expectedCityNamesWithEmptyCityNames, actualCitiesName)
        }
    }

    @Test
    fun `getCities() failed with 500`() {
        runBlocking {
            val mockResponse =
                MockResponse().addHeader("Content-Type", "application/json; charset=utf-8")
                    .addHeader("Cache-Control", "no-cache").setResponseCode(500)
            mockWebServer.enqueue(mockResponse)
            var actualResp: NetworkResponse = NetworkResponse.Failure()
            val expectedResp: NetworkResponse = NetworkResponse.Failure(500, "Server Error")
            weatherApiService.getCities().collect {
                actualResp = it as NetworkResponse.Failure
            }
            Assert.assertEquals(expectedResp, actualResp)
        }
    }

    @Test
    fun `getDetailedWeather() happy path`() {
        runBlocking {
            val mockResponse =
                MockResponse().addHeader("Content-Type", "application/json; charset=utf-8")
                    .addHeader("Cache-Control", "no-cache").setBody(
                        TestUtils.weatherDetailsJsonResp
                    ).setResponseCode(200)
            mockWebServer.enqueue(mockResponse)
            var detailedWeather: DetailedWeather? = null
            weatherApiService.getDetailedWeather(2208791).collect {
                detailedWeather = (it as NetworkResponse.Success).responseBody as DetailedWeather
            }
            Assert.assertEquals(TestUtils.expectedCloudData, detailedWeather?.cloudData)
        }
    }

    @Test
    fun `getDetailedWeather failed with 500`() {
        runBlocking {
            val mockResponse =
                MockResponse().addHeader("Content-Type", "application/json; charset=utf-8")
                    .addHeader("Cache-Control", "no-cache").setResponseCode(500)
            mockWebServer.enqueue(mockResponse)
            var actualResp: NetworkResponse = NetworkResponse.Failure()
            val expectedResp: NetworkResponse = NetworkResponse.Failure(500, "Server Error")
            weatherApiService.getDetailedWeather(900).collect {
                actualResp = it as NetworkResponse.Failure
            }
            Assert.assertEquals(expectedResp, actualResp)
        }
    }
}