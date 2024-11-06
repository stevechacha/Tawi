package com.freshtawi.tawi.domain.usecases

import android.os.Build
import androidx.annotation.RequiresApi
import com.freshtawi.tawi.data.location.ILocationNameGateway
import kotlinx.coroutines.flow.Flow

interface IGetUserLocationNameUseCase {
    suspend fun requestPlaceName(): Flow<String?>
}

class GetLocationNameUseCase(
    private val locationService: ILocationNameGateway,
): IGetUserLocationNameUseCase {
    @RequiresApi(Build.VERSION_CODES.S)
    suspend operator fun invoke(): Flow<String?> = locationService.requestPlaceName()

    override suspend fun requestPlaceName(): Flow<String?> {
        return locationService.requestPlaceName()
    }

}