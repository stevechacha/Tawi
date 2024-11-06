package com.freshtawi.tawi.presentation.screen.auth.registration.tabs.personalDetail

sealed interface PersonalDetailsScreenEffect {
    data class NavigateToFarmerDetailScreen(
        val firstName: String,
        val lastName: String,
        val streetName: String,
        val province: String,
        val county: String,
        val zipCode: String
    ) : PersonalDetailsScreenEffect

    data object NavigateBack : PersonalDetailsScreenEffect
}