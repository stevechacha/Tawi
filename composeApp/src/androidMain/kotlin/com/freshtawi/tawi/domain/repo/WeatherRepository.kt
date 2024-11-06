package com.freshtawi.tawi.domain.repo


import com.freshtawi.tawi.domain.utils.DataResult
import com.freshtawi.tawi.domain.model.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): DataResult<WeatherInfo>
}