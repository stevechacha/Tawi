package com.freshtawi.tawi

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform