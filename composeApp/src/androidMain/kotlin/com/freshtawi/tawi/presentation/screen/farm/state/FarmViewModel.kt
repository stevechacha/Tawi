package com.freshtawi.tawi.presentation.screen.farm.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freshtawi.tawi.domain.model.generateRandomFarmData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FarmViewModel : ViewModel() {

    private val _state = MutableStateFlow(FarmState())
    val state = _state.asStateFlow()

    init {
        generateRandomFarmData()
    }

    fun getData (){
        viewModelScope.launch {
            val randomData = generateRandomFarmData()
            _state.update { it.copy( farmList = randomData) }
        }
    }

    fun onEvent(event: FarmEvent) {
        when (event) {
            is FarmEvent.OnFirstNameChanged -> {
                _state.update { it.copy(firstName = event.firstName) }
            }
            is FarmEvent.OnLastNameChanged -> {
                _state.update { it.copy(lastName = event.lastName) }
            }
            is FarmEvent.onFarmSizeChanged->{
                _state.update { it.copy(farmSize =  event.farmSize) }
            }
            is FarmEvent.OnEmailChanged -> {
                _state.update { it.copy(email = event.email) }
            }
            is FarmEvent.OnPasswordChanged -> {
                _state.update { it.copy(password = event.password) }
            }
            is FarmEvent.OnConfirmPasswordChanged -> {
                _state.update { it.copy(confirmPassword = event.confirmPassword) }
            }
            is FarmEvent.OnStreetNameChanged -> {
                _state.update { it.copy(streetName = event.streetName) }
            }
            is FarmEvent.OnZipCodeChanged -> {
                _state.update { it.copy(zipCode = event.zipCode) }
            }
            is FarmEvent.OnProvinceChanged -> {
                _state.update { it.copy(province = event.province) }
            }
            is FarmEvent.OnCountyChanged -> {
                _state.update { it.copy(county = event.county) }
            }
            is FarmEvent.OnRegisterClick -> {

            }
            is FarmEvent.OnLocationChanged -> {
                _state.update { it.copy(location = event.location) }
            }

        }
    }



}