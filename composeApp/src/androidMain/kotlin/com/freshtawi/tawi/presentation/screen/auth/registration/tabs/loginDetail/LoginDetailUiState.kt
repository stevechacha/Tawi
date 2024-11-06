package com.freshtawi.tawi.presentation.screen.auth.registration.tabs.loginDetail

import com.freshtawi.tawi.domain.model.Account


data class LoginDetailUiState(
    val fullName: String = "",
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val phone: String = "",
    val address: String = "",
    val isLoading: Boolean = false,
    val isUsernameError: Boolean = false,
    val isEmailError: Boolean = false,
    val isPasswordError: Boolean = false,
    val isPhoneError: Boolean = false,
    val isAddressError: Boolean = false,
    val snackbarMessage: String = "",
    val showSnackbar: Boolean = false,
    val account : List<Account> = emptyList()
)
