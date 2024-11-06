package com.freshtawi.tawi.presentation.screen.alerts

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.freshtawi.tawi.presentation.common.composable.util.DestinationGraph
import com.freshtawi.tawi.presentation.common.composable.util.DestinationGraph.ALERTS_SCREEN_ROUTE
import com.freshtawi.tawi.presentation.screen.alerts.AlertsScreen


@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.alertsNavGraph(navController: NavController) {
    composable(route = ALERTS_SCREEN_ROUTE) {
        AlertsScreen(navigateToLogin = { navController.navigate(DestinationGraph.AUTHENTICATION_ROUTE) })
    }
}
