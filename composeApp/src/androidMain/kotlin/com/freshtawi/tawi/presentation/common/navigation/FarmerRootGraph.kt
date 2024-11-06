package com.freshtawi.tawi.presentation.common.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.freshtawi.tawi.presentation.common.bottomNav.StandardScaffold
import com.freshtawi.tawi.presentation.common.bottomNav.getFarmerBottomNavigation
import com.freshtawi.tawi.presentation.common.composable.util.DestinationGraph
import com.freshtawi.tawi.presentation.common.composable.util.DestinationGraph.ALERTS_SCREEN_ROUTE
import com.freshtawi.tawi.presentation.common.composable.util.DestinationGraph.CALENDER_SCREEN_ROUTE
import com.freshtawi.tawi.presentation.common.composable.util.DestinationGraph.FARM_SCREEN_ROUTE
import com.freshtawi.tawi.presentation.common.composable.util.DestinationGraph.PAYMENTS_SCREEN_ROUTE
import com.freshtawi.tawi.presentation.screen.account.accountNavGraph
import com.freshtawi.tawi.presentation.screen.alerts.alertsNavGraph
import com.freshtawi.tawi.presentation.screen.auth.authenticationNavGraph
import com.freshtawi.tawi.presentation.screen.calender.calenderNavGraph
import com.freshtawi.tawi.presentation.screen.detections.navigation.detectionsNavGraph
import com.freshtawi.tawi.presentation.screen.farm.farmNavGraph


fun NavGraphBuilder.farmerRootNavGraph(navController: NavHostController) {
    composable(route = DestinationGraph.FARMER_MAIN_SCREEN) {
        FarmerMainScreen()
    }
}
@Composable
fun FarmerRootGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = FARM_SCREEN_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        accountNavGraph(navController)
        alertsNavGraph(navController)
        authenticationNavGraph(navController)
        calenderNavGraph(navController)
        detectionsNavGraph(navController)
        farmNavGraph(navController)
        paymentsNavGraph(navController)

    }
}


@Composable
fun FarmerMainScreen() {
    val navController = rememberNavController()
    val newBackStackEntry by navController.currentBackStackEntryAsState()

    StandardScaffold(
        navController = navController,
        showBottomBar = shouldFarmerShowBottomBar(newBackStackEntry),
        modifier = Modifier.fillMaxWidth(),
        isLoggedIn = true,
        bottomNavItems = getFarmerBottomNavigation()
    ) {
        FarmerRootGraph(navController)
    }
}

fun shouldFarmerShowBottomBar(backStackStackEntry: NavBackStackEntry?): Boolean {
    return backStackStackEntry?.destination?.route in listOf(
        FARM_SCREEN_ROUTE,
        CALENDER_SCREEN_ROUTE,
        PAYMENTS_SCREEN_ROUTE,
        ALERTS_SCREEN_ROUTE,
    )
}
