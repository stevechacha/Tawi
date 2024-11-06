package com.freshtawi.tawi.presentation.screen.auth.registration.tabs.buyerDetail

import androidx.lifecycle.ViewModel
import com.freshtawi.tawi.domain.model.Account
import com.freshtawi.tawi.presentation.screen.auth.registration.tabs.buyerDetail.BuyerDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BuyerDetailViewModel : ViewModel() {
    private val _state = MutableStateFlow(BuyerDetailUiState())
    val state = _state.asStateFlow()

    private var account = listOf<Account>()

    init {
        loadAccount()
    }

    private fun loadAccount() {
        val acccounts = getAccount()
        account = acccounts
        _state.update { it.copy(account = account) }
    }

    private fun getAccount(): List<Account> {
        return listOf(
            Account(
                buyerCompanyName = state.value.buyerCompanyName,
                buyerShipMethod = state.value.buyerType,
                buyerType = state.value.buyerType,
            )

        )

    }

    fun setCompanyName(companyName: String) {
        _state.update { it.copy(buyerCompanyName = companyName) }
    }

    fun setBuyerType(buyerType: String) {
        _state.update { it.copy(buyerType = buyerType) }
    }

    fun setBuyerReceiveProductModel(buyerReceiveProductModel: String) {
        _state.update { it.copy(buyerReceiveProductModel = buyerReceiveProductModel) }
    }
}