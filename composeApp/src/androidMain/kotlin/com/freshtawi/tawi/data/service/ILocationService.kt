package com.freshtawi.tawi.data.service

interface ILocationService {
    fun isDeviceLocationEnabled(): Boolean
    fun openLocationSettings()
}