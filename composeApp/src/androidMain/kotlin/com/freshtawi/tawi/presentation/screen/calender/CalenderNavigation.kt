package com.freshtawi.tawi.presentation.screen.calender

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.freshtawi.tawi.presentation.screen.calender.weather.WeatherScreen
import com.freshtawi.tawi.presentation.common.composable.util.DestinationGraph.CALENDER_SCREEN_ROUTE


@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.calenderNavGraph(navController: NavController) {
    composable(route = CALENDER_SCREEN_ROUTE) {
        WeatherScreen()
//        CalenderMainScreen()

    }
}


