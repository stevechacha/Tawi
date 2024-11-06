package com.freshtawi.tawi.presentation.screen.auth.registration.tabs.buyerDetail

import com.freshtawi.tawi.presentation.common.base.BaseInteractionListener

interface BuyerDetailScreenInteractionListener : BaseInteractionListener {
    fun onCompanyNameChanged(companyName: String)
    fun onBuyerTypeChanged(buyerType: String)
    fun onBuyerReceiveProductModelChanged(buyerReceiveProductModel: String)
    fun onContinueButtonClicked()
    fun onBackButtonClicked()
}