package com.freshtawi.tawi.data.location

import kotlinx.coroutines.flow.Flow


interface ILocationNameGateway {
    suspend fun requestPlaceName(): Flow<String?>
}