package com.freshtawi.tawi.presentation.screen.auth.registration.tabs.buyerDetail

import com.freshtawi.tawi.domain.model.Account

data class BuyerDetailUiState(
    val buyerCompanyName: String = "",
    val userType: String = "Buyer",
    val buyerType: String = "",
    val buyerReceiveProductModel: String = "",
    val account:List<Account> = emptyList()
)
