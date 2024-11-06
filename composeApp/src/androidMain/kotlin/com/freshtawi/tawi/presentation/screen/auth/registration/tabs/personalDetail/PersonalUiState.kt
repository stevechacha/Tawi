package com.freshtawi.tawi.presentation.screen.auth.registration.tabs.personalDetail

import com.freshtawi.tawi.domain.model.Account


data class PersonalUiState(
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
    val account: List<Account> = emptyList()
)