package com.freshtawi.tawi.domain.usecases

import android.os.Build
import androidx.annotation.RequiresApi
import com.freshtawi.tawi.domain.model.LocationModel
import com.freshtawi.tawi.data.location.ILocationGateway
import kotlinx.coroutines.flow.Flow


interface IGetUserLocationUseCase {
    suspend fun requestLocationUpdates(): Flow<LocationModel?>

    suspend  fun requestCurrentLocation(): Flow<LocationModel?>

}

class GetLocationUseCase(
    private val locationService: ILocationGateway,
): IGetUserLocationUseCase {
    @RequiresApi(Build.VERSION_CODES.S)
    suspend operator fun invoke(): Flow<LocationModel?> = locationService.requestLocationUpdates()

    override suspend fun requestLocationUpdates(): Flow<LocationModel?> {
        return locationService.requestLocationUpdates()
    }

    override suspend fun requestCurrentLocation(): Flow<LocationModel?> {
        return locationService.requestCurrentLocation()
    }


}