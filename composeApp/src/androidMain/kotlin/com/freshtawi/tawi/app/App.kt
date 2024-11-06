package com.freshtawi.tawi.app

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.freshtawi.tawi.presentation.common.navigation.AppNavigation
import com.freshtawi.tawi.presentation.common.navigation.Screens
import com.freshtawi.tawi.presentation.common.bottomNav.StandardScaffold
import com.freshtawi.tawi.presentation.common.navigation.RootGraph
import org.koin.compose.KoinContext

@Composable
fun App() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    MaterialTheme {
        KoinContext {

        }
    }
}



fun shouldShowBottomBar(backStackStackEntry: NavBackStackEntry?): Boolean {
    return backStackStackEntry?.destination?.route in listOf(
        Screens.Home.route,
        Screens.History.route,
        Screens.Account.route,
    )
}