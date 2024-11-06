package com.freshtawi.tawi.presentation.screen.auth.registration.registerOptions

import androidx.lifecycle.ViewModel
import com.freshtawi.tawi.domain.model.Account
import com.freshtawi.tawi.presentation.screen.auth.registration.registerOptions.RegisterOptionUiIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterOptionViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(RegisterOptionUiIState())
    val uiState = _uiState.asStateFlow()

    private var account = listOf<Account>()

    init {
        loadAccounts()
    }

    private fun loadAccounts() {
        val acccounts = getAccount()
        account = acccounts
        _uiState.update { it.copy(account = account) }
    }

    fun onFarmerSelected(farmer: String){
        _uiState.update { it.copy(farmer = farmer) }
    }

    fun onBuyerSelected(buyer: String){
        _uiState.update { it.copy(buyer = buyer) }
    }

    private fun getAccount(): List<Account> {
        return listOf(
            Account(
            id = "Farmer",
            fullName = "",
            username = "",
            email = "",
            address = "",
            phone = "",
            password = ""
            )
        )
    }


}