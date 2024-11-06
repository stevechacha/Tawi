package com.freshtawi.tawi.presentation.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.freshtawi.tawi.presentation.common.composable.util.DestinationGraph.AUTHENTICATION_ROUTE
import com.freshtawi.tawi.presentation.common.composable.util.DestinationGraph.BUYER_MAIN_SCREEN
import com.freshtawi.tawi.presentation.common.composable.util.DestinationGraph.PAYMENTS_SCREEN_ROUTE
import com.freshtawi.tawi.presentation.screen.account.accountNavGraph
import com.freshtawi.tawi.presentation.screen.alerts.alertsNavGraph
import com.freshtawi.tawi.presentation.screen.auth.authenticationNavGraph
import com.freshtawi.tawi.presentation.screen.browse.browseNavGraph
import com.freshtawi.tawi.presentation.screen.calender.calenderNavGraph
import com.freshtawi.tawi.presentation.screen.detections.navigation.detectionsNavGraph
import com.freshtawi.tawi.presentation.screen.farm.farmNavGraph
import com.freshtawi.tawi.presentation.screen.payment.PaymentScreen
import org.koin.compose.currentKoinScope


@Composable
fun RootGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = AUTHENTICATION_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        accountNavGraph(navController)
        alertsNavGraph(navController)
        browseNavGraph(navController)
        calenderNavGraph(navController)
        detectionsNavGraph(navController)
        farmNavGraph(navController)
        paymentsNavGraph(navController)
        authenticationNavGraph(navController)
        buyerRootNavGraph(navController)
        farmerRootNavGraph(navController)

    }
}
@Composable
inline fun <reified T: ViewModel> koinViewModel(): T {
    val scope = currentKoinScope()
    return viewModel {
        scope.get<T>()
    }
}
fun NavGraphBuilder.paymentsNavGraph(navController: NavHostController) {
    composable(route = PAYMENTS_SCREEN_ROUTE) {
        PaymentScreen()
    }
}
