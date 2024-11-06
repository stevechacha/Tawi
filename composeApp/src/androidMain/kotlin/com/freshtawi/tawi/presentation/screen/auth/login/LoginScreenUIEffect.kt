package com.freshtawi.tawi.presentation.screen.auth.login

sealed class LoginScreenUIEffect {
    data object NavigateToHome : LoginScreenUIEffect()
    data object NavigateToSignup : LoginScreenUIEffect()

    data  object NavigateToForgotPassword : LoginScreenUIEffect()



}
