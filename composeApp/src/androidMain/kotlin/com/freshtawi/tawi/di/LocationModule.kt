package com.freshtawi.tawi.di

import com.freshtawi.tawi.data.location.LocationNameGateway
import com.google.android.gms.location.LocationServices
import com.freshtawi.tawi.data.location.LocationGateway
import com.freshtawi.tawi.data.service.DefaultLocationTracker
import com.freshtawi.tawi.data.service.LocationService
import com.freshtawi.tawi.data.location.ILocationGateway
import com.freshtawi.tawi.data.location.ILocationNameGateway
import com.freshtawi.tawi.data.service.ILocationService
import com.freshtawi.tawi.data.service.LocationTracker
import org.koin.dsl.module
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext


val locationModule = module {
    single<ILocationGateway> {
        LocationGateway(
            context = androidContext().applicationContext,
            LocationServices.getFusedLocationProviderClient(androidContext())
        )
    }

    single<ILocationNameGateway> {
        LocationNameGateway(
            context = androidContext().applicationContext,
            LocationServices.getFusedLocationProviderClient(androidContext())
        )
    }

    single<ILocationService> { LocationService(androidContext().applicationContext) }

    single<LocationTracker> {
        DefaultLocationTracker(
            application = androidApplication(),
            LocationServices.getFusedLocationProviderClient(androidContext())
        )
    }


}