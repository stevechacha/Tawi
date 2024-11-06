package com.freshtawi.tawi.presentation.screen.auth.registration.tabs.farmerDetail

import com.freshtawi.tawi.domain.model.Account

data class FarmerDetailUiState(
    val userTypeType: String = "Farmer",
    val farmerProductName: String = "",
    val farmerShipProductModel: String = "",
    val account : List<Account> = listOf<Account>()
)
