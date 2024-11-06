package com.freshtawi.tawi.data.network.response

import com.squareup.moshi.Json
import com.freshtawi.tawi.data.network.response.WeatherDataDto

data class WeatherDto(
    @field:Json(name = "hourly")
    val weatherData: WeatherDataDto
)
