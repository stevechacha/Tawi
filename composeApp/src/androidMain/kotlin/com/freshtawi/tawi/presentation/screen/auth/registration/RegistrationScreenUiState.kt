package com.freshtawi.tawi.presentation.screen.auth.registration

import com.freshtawi.tawi.domain.model.Account

data class RegistrationScreenUiState(
    val buyerCompanyName: String = "",
    val userType: String = "Buyer",
    val buyerType: String = "",
    val buyerReceiveProductModel: String = "",
    val userTypeType: String = "Farmer",
    val farmerProductName: String = "",
    val farmerShipProductModel: String = "",
    val fullName: String = "",
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val phone: String = "",
    val address: String = "",
    val isUsernameError: Boolean = false,
    val isEmailError: Boolean = false,
    val isPasswordError: Boolean = false,
    val snackbarMessage: String = "",
    val showSnackbar: Boolean = false,
    val firstName: String = "",
    val lastName: String = "",
    val streetName: String = "",
    val zipCode: String = "",
    val province: String = "",
    val county: String = "",
    val isFullNameError: Boolean = false,
    val isPhoneError: Boolean = false,
    val isAddressError: Boolean = false,
    val isLoading: Boolean = false,
    val account: List<Account> = emptyList(),
    val farmer: String = "Farmer",
    val buyer: String = "Buyer",

    )
