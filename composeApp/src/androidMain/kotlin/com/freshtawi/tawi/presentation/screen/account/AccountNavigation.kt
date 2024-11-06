package com.freshtawi.tawi.presentation.screen.account

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.freshtawi.tawi.presentation.screen.account.aboutUs.AboutUsScreen
import com.freshtawi.tawi.presentation.screen.account.changeLanguage.ChangeLanguageScreen
import com.freshtawi.tawi.presentation.screen.account.changePassword.ChangePasswordScreen
import com.freshtawi.tawi.presentation.screen.account.security.SecurityScreen
import com.freshtawi.tawi.presentation.screen.account.support.SupportScreen
import com.freshtawi.tawi.presentation.common.composable.util.DestinationGraph.ACCOUNTS_SCREEN_ROUTE

const val CHANGE_LANGUAGE_ROUTE = "change_language"
const val CHANGE_PASSWORD_ROUTE = "change_password"
const val SECURITY_SCREEN_ROUTE = "security"
const val SUPPORT_SCREEN_ROUTE = "support"
const val ABOUT_SCREEN_ROUTE = "about_us"

fun NavGraphBuilder.accountNavGraph(navController: NavController) {
    composable(route = ACCOUNTS_SCREEN_ROUTE) {
        AccountScreen(navController)
    }
    composable(route = ABOUT_SCREEN_ROUTE) {
        AboutUsScreen()
    }

    composable(CHANGE_PASSWORD_ROUTE) {
        ChangePasswordScreen()
    }
    composable(CHANGE_LANGUAGE_ROUTE) {
        ChangeLanguageScreen()
    }
    composable(SECURITY_SCREEN_ROUTE) {
        SecurityScreen()
    }
    composable(SUPPORT_SCREEN_ROUTE) {
        SupportScreen()
    }
}