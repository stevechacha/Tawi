package com.freshtawi.tawi.presentation.screen.calender.weather

import com.freshtawi.tawi.domain.model.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
