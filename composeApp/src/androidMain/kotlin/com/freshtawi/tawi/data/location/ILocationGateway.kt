package com.freshtawi.tawi.data.location

import com.freshtawi.tawi.domain.model.LocationModel
import kotlinx.coroutines.flow.Flow

interface ILocationGateway {

    suspend fun requestLocationUpdates(): Flow<LocationModel?>

    suspend  fun requestCurrentLocation(): Flow<LocationModel?>

}