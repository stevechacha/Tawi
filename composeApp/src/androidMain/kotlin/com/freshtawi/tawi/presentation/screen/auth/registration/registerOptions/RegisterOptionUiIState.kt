package com.freshtawi.tawi.presentation.screen.auth.registration.registerOptions

import com.freshtawi.tawi.domain.model.Account

data class RegisterOptionUiIState(
    val farmer: String = "Farmer",
    val buyer: String = "Buyer",
    val account: List<Account>  = listOf<Account>()
)
