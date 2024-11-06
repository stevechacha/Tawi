package com.freshtawi.tawi.utils

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform