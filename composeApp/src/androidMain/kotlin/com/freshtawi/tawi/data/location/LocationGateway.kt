package com.freshtawi.tawi.data.location

import android.content.Context
import android.annotation.SuppressLint
import android.os.Build
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import com.freshtawi.tawi.data.mapper.toLocation
import com.freshtawi.tawi.data.util.hasLocationPermission
import com.freshtawi.tawi.domain.model.LocationModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber

class LocationGateway constructor(
    private val context: Context,
    private val locationClient: FusedLocationProviderClient
) : ILocationGateway {
    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.S)
    override suspend fun requestLocationUpdates(): Flow<LocationModel?> = callbackFlow {
        if (!context.hasLocationPermission()) {
            trySend(null)
            return@callbackFlow
        }

        val request = LocationRequest.Builder(10000L)
            .setIntervalMillis(10000L)
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            .build()

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.locations.lastOrNull()?.let {
                    trySend(LatLng(it.latitude, it.longitude).toLocation())
                    Log.i("locationExample", "onLocationResult: $it")
                }
            }
        }

        locationClient.requestLocationUpdates(
            request,
            locationCallback,
            Looper.getMainLooper()
        )

        awaitClose {
            locationClient.removeLocationUpdates(locationCallback)
        }
    }

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.S)
    override suspend fun requestCurrentLocation(): Flow<LocationModel?> = callbackFlow {
        if (!context.hasLocationPermission()) {
            trySend(null)
            return@callbackFlow
        }

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.locations.lastOrNull()?.let {
                    trySend(LatLng(it.latitude, it.longitude).toLocation())
                    Timber.tag("locationExample").i("onLocationResult (current location): " + it)
                    close()
                }
            }
        }

        locationClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { location: android.location.Location? ->
                location?.let {
                    trySend(LatLng(it.latitude, it.longitude).toLocation())
                    Timber.tag("locationExample").i("onSuccess (current location): " + location)
                    close()
                }
            }
            .addOnFailureListener { e: Exception ->
                Timber.tag("locationExample").e("onFailure (current location): " + e)
                close(e)
            }

        awaitClose {
            // This block is intentionally left blank as the cleanup is done in the success or failure listeners.
        }
    }

}
