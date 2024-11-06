package com.freshtawi.tawi.presentation.screen.auth.registration.tabs.personalDetail

import com.freshtawi.tawi.presentation.common.base.BaseInteractionListener

interface PersonalDetailInteractionListener : BaseInteractionListener {
    fun onFirstNameChanged(firstName: String)
    fun onLastNameChanged(lastName: String)
    fun onStreetInformationChanged(streetName: String)
    fun onProvinceChanged(province: String)
    fun onCountyChanged(county: String)
    fun onZipCodeChanged(zipCode: String)
    fun onContinueButtonClicked(userType: String)
    fun onBackButtonClicked()
}