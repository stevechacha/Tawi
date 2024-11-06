package com.freshtawi.tawi.presentation.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.freshtawi.tawi.presentation.screen.auth.registration.tabs.loginDetail.LoginDetailsScreen
import org.koin.compose.currentKoinScope


@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: Screens = Screens.Register,
) {
    NavHost(
        modifier = Modifier.then(modifier),
        startDestination = startDestination.route,
        navController = navController,
    ) {

        composable(Screens.OnBoarding.route) {
//            val onBoardViewModel = koinViewModel<OnBoardViewModel>()
            LoginDetailsScreen()
        }

    }
}



sealed class Screens(val route: String) {
    data object OnBoarding : Screens("gallery")
    data object Login: Screens("login")
    data object Register: Screens("register")
    data object ConfirmRegisterCode: Screens("register_code")
    data object Otp: Screens("otp")
    data object Home: Screens("home")
    data object History: Screens("history")
    data object Account: Screens("account")
    data object ArtsScreen : Screens("arts")
    data object DetailScreen : Screens("detail/{id}")
    data object CollectionScreen : Screens("collection")
}


