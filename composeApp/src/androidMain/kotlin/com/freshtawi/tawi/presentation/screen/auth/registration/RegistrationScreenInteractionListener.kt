package com.freshtawi.tawi.presentation.screen.auth.registration

import com.freshtawi.tawi.presentation.common.base.BaseInteractionListener
import kotlinx.coroutines.flow.update

interface RegistrationScreenInteractionListener: BaseInteractionListener {
    fun setCompanyName(companyName: String)
    fun setBuyerType(buyerType: String)
    fun setBuyerReceiveProductModel(buyerReceiveProductModel: String)
    fun setFarmerProductName(farmerProduct: String)
    fun setFarmerShipProductModel(farmerShipProductModel: String)
    fun onEmailChanged(email: String)
    fun onPasswordChanged(password: String)
    fun onConfirmPasswordChanged(confirmPassword: String)
    fun onPhoneChanged(phone: String)
    fun onSignUpButtonClicked()
    fun onFirstNameChanged(firstName: String)
    fun onLastNameChanged(lastName: String)
    fun onStreetInformationChanged(streetName: String)
    fun onProvinceChanged(province: String)
    fun onCountyChanged(county: String)
    fun onZipCodeChanged(zipCode: String)
    fun onContinueButtonClicked(userType: String)
    fun onBackButtonClicked()
    fun onFarmerSelected(farmer: String)
    fun onBuyerSelected(buyer: String)
}