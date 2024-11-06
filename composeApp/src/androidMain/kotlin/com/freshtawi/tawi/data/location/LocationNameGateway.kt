package com.freshtawi.tawi.data.location

import android.content.Context
import android.annotation.SuppressLint
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Looper
import androidx.annotation.RequiresApi
import com.freshtawi.tawi.data.util.hasLocationPermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber
import java.util.Locale


class LocationNameGateway constructor(
    private val context: Context,
    private val locationClient: FusedLocationProviderClient
) : ILocationNameGateway {
    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.S)
    override suspend fun requestPlaceName(): Flow<String?> = callbackFlow {
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
                locationResult.locations.lastOrNull()?.let { location ->
                    // Use Geocoder to get the address from the coordinates
                    val geocoder = Geocoder(context, Locale.getDefault())
                    val addresses: List<Address>? =
                        geocoder.getFromLocation(location.latitude, location.longitude, 1)

                    val placeName = addresses?.firstOrNull()?.getAddressLine(0)
                    trySend(placeName)
                    Timber.tag("locationExample").i("onLocationResult (place name): %s", placeName)
                    close()
                }
            }
        }
        locationClient.requestLocationUpdates(
            request,
            locationCallback,
            Looper.getMainLooper()
        )

        locationClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    // Use Geocoder to get the address from the coordinates
                    val geocoder = Geocoder(context, Locale.getDefault())
                    val addresses: List<Address>? =
                        geocoder.getFromLocation(location.latitude, location.longitude, 1)

                    val placeName = addresses?.firstOrNull()?.getAddressLine(0)
                    trySend(placeName)
                    Timber.tag("locationExample").i("onSuccess (place name): %s", placeName)
                    close()
                }
            }
            .addOnFailureListener { e: Exception ->
                Timber.tag("locationExample").e("onFailure (place name): %s", e)
                close(e)
            }

        awaitClose {
            // This block is intentionally left blank as the cleanup is done in the success or failure listeners.
        }
    }

}
