package com.freshtawi.tawi.data.service

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}