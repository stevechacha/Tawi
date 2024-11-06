package com.freshtawi.tawi.presentation.screen.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.freshtawi.tawi.presentation.screen.auth.forgotPassword.ForgotPasswordScreen
import com.freshtawi.tawi.presentation.screen.auth.login.MainLoginScreen
import com.freshtawi.tawi.presentation.screen.auth.pickLanguage.PickLanguageScreen
import com.freshtawi.tawi.presentation.screen.auth.registration.RegistrationScreen
import com.freshtawi.tawi.presentation.screen.auth.registration.registerOptions.RegisterOptionScreen
import com.freshtawi.tawi.presentation.screen.auth.welcome.WelcomeScreen
import com.freshtawi.tawi.presentation.common.composable.util.DestinationGraph
import com.freshtawi.tawi.presentation.common.composable.util.DestinationGraph.AUTHENTICATION_ROUTE


const val LANGUAGE_CODE = "code"
const val WELCOME_ROUTE = "welcome/{$LANGUAGE_CODE}"
const val REGISTER_OPTION_ROUTE = "register_option"
const val LOGIN_ROUTE = "login"

fun NavGraphBuilder.authenticationNavGraph(navController: NavHostController) {
    composable(route = AUTHENTICATION_ROUTE) {
        PickLanguageScreen(
            navigateToAuth = { code ->
                navController.navigate(WELCOME_ROUTE.replace(LANGUAGE_CODE, code))
            }
        )
    }
    composable(
        route = WELCOME_ROUTE,
        arguments = listOf(navArgument(LANGUAGE_CODE) { type = NavType.StringType })
    ) {
        val code = it.arguments?.getString(LANGUAGE_CODE)
            ?: throw IllegalStateException("code data missing.")
        WelcomeScreen(
            code = code,
            onNavigateToRegister = {
                navController.navigate(REGISTER_OPTION_ROUTE)
            },
            onNavigateToLogin = {
                navController.navigate(LOGIN_ROUTE)
            }
        )
    }
    composable(
        route = REGISTER_OPTION_ROUTE
    ) {
        RegisterOptionScreen(
            navigateToFarmerRegisterDetail = { userTypes ->
                navController.navigate(
                    AuthenticationScreens.Registration.route.replace(
                        oldValue = "{userType}",
                        newValue = userTypes
                    )
                )
            },
            navigateToBuyerRegisterDetail = { userTypes->
                navController.navigate(
                    AuthenticationScreens.Registration.route.replace(
                        oldValue = "{userType}",
                        newValue = userTypes
                    )
                )
            }
        )
    }
    composable(
        route =  AuthenticationScreens.Registration.route,
        arguments = listOf(navArgument("userType") { type = NavType.StringType })
    ){
        val userType = it.arguments?.getString("userType")
            ?: throw IllegalStateException("userType data missing.")
        RegistrationScreen(
            userType = userType ,
            onNavigateToLogin = { navController.navigate(LOGIN_ROUTE)}
        )
    }
   /* composable(
        route = AuthenticationScreens.BuyerDetailRoute.route,
    ) {
        BuyerDetailScreen(
            navigateToPersonalDetail = { userTypes ->
                navController.navigate(
                    AuthenticationScreens.PersonalDetailsRoute.route.replace(
                        oldValue = "{userType}",
                        newValue = userTypes
                    )
                )
            }
        )

    }

    composable(
        route = AuthenticationScreens.FarmerDetailsRoute.route,
    ) {
        FarmerDetailsScreen(
            navigateToPersonalDetail = { userTypes ->
                navController.navigate(
                    AuthenticationScreens.PersonalDetailsRoute.route.replace(
                        oldValue = "{userType}",
                        newValue = userTypes
                    )
                )
            }
        )

    }

    composable(
        route = AuthenticationScreens.PersonalDetailsRoute.route,
        arguments = listOf(navArgument("userType") { type = NavType.StringType })
    ) {
        val userType = it.arguments?.getString("userType")
            ?: throw IllegalStateException("userType data missing.")
        PersonalDetailsScreen(
            userType = userType,
            onNavigateToLoginDetails = { userTye ->
                navController.navigate(AuthenticationScreens.LoginDetailsRoute.route.replace(
                    oldValue = "{userType}",
                    newValue = userTye)
                )
            }
        )
    }
    composable(
        route = AuthenticationScreens.LoginDetailsRoute.route,
        arguments = listOf(navArgument("userType") { type = NavType.StringType })

    ) {
        val userType = it.arguments?.getString("userType") ?: throw IllegalStateException("userType data missing.")
        LoginDetailsScreen(
            userType = userType,
            onNavigateToSmsVerification = {
                navController.navigate(AuthenticationScreens.Verification.route.replace(
                    oldValue = "{userType}",
                    newValue = it.toString()
                ))
            }
        )
    }

    composable(
        route = AuthenticationScreens.Verification.route,
        arguments = listOf(navArgument("userType") { type = NavType.StringType })
    ) {
        val userType = it.arguments?.getString("userType") ?: throw IllegalStateException("userType data missing.")

        SmsVerificationScreen(
            userType = userType,
            onNavigateToSuccessVerification = {
                navController.navigate(AuthenticationScreens.VerificationSuccess.route.replace(
                    oldValue = "{userType}",
                    newValue = it.toString()
                ))
            }
        )
    }
    composable(
        route = AuthenticationScreens.VerificationSuccess.route,
        arguments = listOf(navArgument("userType") { type = NavType.StringType })
    ) {
        val userType = it.arguments?.getString("userType") ?: throw IllegalStateException("userType data missing.")
        SuccessVerificationScreen(
            userType = userType,
            onNavigateToFarmerGraph = {
                navController.navigate(DestinationGraph.FARMER_MAIN_SCREEN)
            },
            onNavigateToBuyerNavGraph = {
                navController.navigate(DestinationGraph.BUYER_MAIN_SCREEN)
            },
        )

    }*/

    composable(route = LOGIN_ROUTE) {
        MainLoginScreen(
            onClickNavigate = {
                navController.navigate(DestinationGraph.FARMER_MAIN_SCREEN)
            },
            onNavigateToForgotPassword = {
                navController.navigate(AuthenticationScreens.ForgotPassword.route)
            },
            onCreateAccountClick = {navController.navigate(REGISTER_OPTION_ROUTE)}
        )
    }

    composable(route = AuthenticationScreens.ForgotPassword.route) {
        ForgotPasswordScreen()
    }





}

sealed class AuthenticationScreens(val route: String) {
    data object WelcomeRoute : AuthenticationScreens("/welcome/{code}")
    data object FarmerDetailsRoute : AuthenticationScreens("/farmer_detail/{userType}")
    data object Registration : AuthenticationScreens("/registration/{userType}")
    data object BuyerDetailRoute : AuthenticationScreens("/buyer_detail/{userType}")
    data object Login : AuthenticationScreens("/login")
    data object RegisterOption : AuthenticationScreens("/register")
    data object ForgotPassword : AuthenticationScreens("/forgot_password")
    data object LoginDetailsRoute : AuthenticationScreens("/farmer_login_details/{userType}")
    data object PersonalDetailsRoute : AuthenticationScreens("/farmer_personal_details/{userType}")
    data object Verification : AuthenticationScreens("/Verification/{userType}")
    data object VerificationSuccess : AuthenticationScreens("/Verification_Success/{userType}")


}
