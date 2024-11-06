package com.freshtawi.tawi.data.repo


import com.freshtawi.tawi.data.mapper.toWeatherInfo
import com.freshtawi.tawi.data.network.api.WeatherApi
import com.freshtawi.tawi.domain.utils.DataResult
import com.freshtawi.tawi.domain.model.WeatherInfo
import com.freshtawi.tawi.domain.repo.WeatherRepository

class WeatherRepositoryImpl constructor(
    private val api: WeatherApi
): WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): DataResult<WeatherInfo> {
        return try {
            DataResult.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toWeatherInfo()
            )
        } catch(e: Exception) {
            e.printStackTrace()
            DataResult.Error(e.message ?: "An unknown error occurred.")
        }
    }
}