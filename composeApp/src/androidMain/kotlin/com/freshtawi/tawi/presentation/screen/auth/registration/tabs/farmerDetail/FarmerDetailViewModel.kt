package com.freshtawi.tawi.presentation.screen.auth.registration.tabs.farmerDetail

import androidx.lifecycle.ViewModel
import com.freshtawi.tawi.domain.model.Account
import com.freshtawi.tawi.presentation.screen.auth.registration.tabs.farmerDetail.FarmerDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FarmerDetailViewModel : ViewModel() {
    private val _state = MutableStateFlow(FarmerDetailUiState())
    val state = _state.asStateFlow()

    private var account = listOf<Account>()

    init {
        loadAccount()
    }

    private fun loadAccount(){
        val acccounts = getAccount()
        account = acccounts
        _state.update { it.copy(account = account) }
    }

    fun setFarmerProductName(farmerProduct: String) {
        _state.update { it.copy(farmerProductName = farmerProduct) }
    }

    fun setFarmerShipProductModel(farmerShipProductModel: String) {
        _state.update { it.copy(farmerShipProductModel = farmerShipProductModel) }
    }

   private fun getAccount(): List<Account> {
        return listOf(
            Account(
               farmerProductName = state.value.farmerProductName,
                farmerShipMethod = state.value.farmerShipProductModel
            )
        )
    }
}