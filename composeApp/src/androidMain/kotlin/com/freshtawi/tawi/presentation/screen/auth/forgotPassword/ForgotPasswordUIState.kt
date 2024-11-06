package com.freshtawi.tawi.presentation.screen.auth.forgotPassword

data class ForgotPasswordUIState(
    val email: String = "",
    val isEmailError: Boolean = false,
    val isLoading: Boolean = false,
    val snackbarMessage: String = "",
    val showSnackbar: Boolean = false
)
