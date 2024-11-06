package com.freshtawi.tawi.presentation.screen.farm.state

sealed class FarmEvent{
    data class OnEmailChanged(val email: String): FarmEvent()
    data class OnPasswordChanged(val password: String): FarmEvent()
    data class OnConfirmPasswordChanged(val confirmPassword: String): FarmEvent()
    data class OnFirstNameChanged(val firstName: String): FarmEvent()
    data class OnLastNameChanged(val lastName: String): FarmEvent()
    data class OnStreetNameChanged(val streetName: String): FarmEvent()
    data class OnZipCodeChanged(val zipCode: String): FarmEvent()
    data class OnProvinceChanged(val province: String): FarmEvent()
    data class OnCountyChanged(val county: String): FarmEvent()
    data class OnLocationChanged(val location: String): FarmEvent()

    data class onFarmSizeChanged(val farmSize: String): FarmEvent()
    object OnRegisterClick: FarmEvent()


}
