package com.freshtawi.tawi

import com.freshtawi.tawi.utils.getPlatform

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}