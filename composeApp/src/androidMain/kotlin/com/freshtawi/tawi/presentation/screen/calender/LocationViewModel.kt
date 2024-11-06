package com.freshtawi.tawi.presentation.screen.calender

import android.location.Location
import androidx.lifecycle.ViewModel

class LocationViewModel : ViewModel() {
   /* private val _uiState = MutableStateFlow(MyCityUiState)
    val uiState: StateFlow<MyCityUiState> = _uiState
    fun updateCurrentLocation(location: Location?) {
        _uiState.up
        _uiState.update { it.copy(
                currentLocation = location
            )
        }
    }

    fun displayDistance(placeLocation: LatLng, context: Context): String? {

        var fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    updateCurrentLocation(location)
                }
            }

        var result: Float?
        var formatResult: String? = null

        val placeLocationToLocationType = Location("Place")
        placeLocationToLocationType.latitude = placeLocation.latitude
        placeLocationToLocationType.longitude = placeLocation.longitude

        result =
            uiState.value.currentLocation?.distanceTo(placeLocationToLocationType)

        if (result != null) {
            formatResult = "%.1f".format(result / 1000) + " km"
        }
        return formatResult
    }*/


}

data class MyCityUiState(
    val currentLocation: Location? = null
)
