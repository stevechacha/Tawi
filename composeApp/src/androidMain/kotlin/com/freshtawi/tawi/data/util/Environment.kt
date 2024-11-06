package com.freshtawi.tawi.data.util

sealed class Environment(val url: String) {
    data object Main : Environment("https://api.open-meteo.com/")
}
