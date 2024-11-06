package com.freshtawi.tawi.presentation.common.navigation

import android.annotation.SuppressLint
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
import com.freshtawi.tawi.presentation.common.bottomNav.getBuyerBottomNavigation
import com.freshtawi.tawi.presentation.common.composable.util.DestinationGraph
import com.freshtawi.tawi.presentation.screen.account.accountNavGraph
import com.freshtawi.tawi.presentation.screen.auth.authenticationNavGraph
import com.freshtawi.tawi.presentation.screen.browse.browseNavGraph


fun NavGraphBuilder.buyerRootNavGraph(navController: NavHostController) {
    composable(route = DestinationGraph.BUYER_MAIN_SCREEN) {
        BuyerMainScreen()
    }
}


@SuppressLint("SuspiciousIndentation", "UnusedBoxWithConstraintsScope")
@Composable
fun BuyerMainScreen() {
    val navController = rememberNavController()
    val newBackStackEntry by navController.currentBackStackEntryAsState()

    StandardScaffold(
        navController = navController,
        showBottomBar = shouldShowBottomBar(newBackStackEntry),
        modifier = Modifier.fillMaxWidth(),
        isLoggedIn = true,
        bottomNavItems = getBuyerBottomNavigation()
    ) {
        BuyerRootGraph(navController)
    }

}


@Composable
fun BuyerRootGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = DestinationGraph.BROWS_SCREEN_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        accountNavGraph(navController)
        authenticationNavGraph(navController)
        browseNavGraph(navController)
        paymentsNavGraph(navController)
        buyerRootNavGraph(navController)
    }
}


fun shouldShowBottomBar(backStackStackEntry: NavBackStackEntry?): Boolean {
    return backStackStackEntry?.destination?.route in listOf(
        DestinationGraph.BROWS_SCREEN_ROUTE,
        DestinationGraph.PAYMENTS_SCREEN_ROUTE,
        DestinationGraph.ACCOUNTS_SCREEN_ROUTE,
    )
}