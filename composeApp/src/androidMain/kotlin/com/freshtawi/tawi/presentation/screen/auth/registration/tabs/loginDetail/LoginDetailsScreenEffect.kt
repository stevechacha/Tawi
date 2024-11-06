package com.freshtawi.tawi.presentation.screen.auth.registration.tabs.loginDetail

sealed interface LoginDetailsScreenEffect {
    data object NavigateToLoginScreen : LoginDetailsScreenEffect
    data object NavigateBack : LoginDetailsScreenEffect
}
