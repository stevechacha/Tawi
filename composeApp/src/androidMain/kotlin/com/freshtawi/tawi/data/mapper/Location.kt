package com.freshtawi.tawi.data.mapper

import com.freshtawi.tawi.domain.model.Location
import com.freshtawi.tawi.domain.model.LocationModel
import com.google.android.gms.maps.model.LatLng
import com.freshtawi.tawi.data.network.response.LocationDto


fun LocationDto.toEntity() = Location(
    latitude = latitude ?: 0.0,
    longitude = longitude ?: 0.0
)

fun LatLng.toLocation(): LocationModel {
    return LocationModel(this.latitude, this.longitude)
}
