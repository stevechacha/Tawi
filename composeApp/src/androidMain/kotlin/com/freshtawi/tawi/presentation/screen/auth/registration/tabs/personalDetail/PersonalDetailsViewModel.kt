package com.freshtawi.tawi.presentation.screen.auth.registration.tabs.personalDetail

import com.freshtawi.tawi.domain.utils.AuthorizationException
import com.freshtawi.tawi.domain.usecases.validation.IValidationUseCase
import com.freshtawi.tawi.domain.model.Account
import com.freshtawi.tawi.presentation.common.base.BaseScreenModel

class PersonalDetailsViewModel(
    private val validation: IValidationUseCase
) : BaseScreenModel<PersonalUiState, PersonalDetailsScreenEffect>(PersonalUiState()),
    PersonalDetailInteractionListener {
        private var account = listOf<Account>()

     init {
         loadAccount()
     }

    private fun loadAccount (){
        val accounts = getAccount()
        account = accounts
        updateState { it.copy(account = account) }

    }

    private fun getAccount():List<Account> {
        return listOf(
            Account(
                firstName = state.value.firstName,
                lastName = state.value.lastName,
                streetName = state.value.streetName,
                province = state.value.province,
                county = state.value.county,
                zipCode = state.value.zipCode,
            )
        )
    }

    override fun onFirstNameChanged(firstName: String) {
        updateState { it.copy(firstName = firstName) }
    }

    override fun onLastNameChanged(lastName: String) {
        updateState { it.copy(lastName = lastName) }
    }

    override fun onStreetInformationChanged(streetName: String) {
        updateState { it.copy(streetName = streetName) }
    }

    override fun onProvinceChanged(province: String) {
        updateState { it.copy(province = province) }
    }

    override fun onCountyChanged(county: String) {
        updateState { it.copy(county = county) }
    }

    override fun onZipCodeChanged(zipCode: String) {
        updateState { it.copy(zipCode = zipCode) }
    }

    override fun onContinueButtonClicked(userType: String) {
        with(state.value) {
            val fullName = "$firstName $lastName"
            tryCatch {
                validation.validateFullName(fullName)
                validation.validateAddress(streetName)
                sendNewEffect(
                    PersonalDetailsScreenEffect.NavigateToFarmerDetailScreen(
                        firstName = firstName,
                        lastName = lastName,
                        streetName = streetName,
                        province = province,
                        county = county,
                        zipCode = zipCode
                    )
                )
            }
        }
    }

   /* override fun onContinueButtonClicked() {
        with(state.value) {
            val fullName = "$firstName $lastName"
            tryCatch {
                validation.validateFullName(fullName)
                validation.validateAddress(streetName)
                sendNewEffect(
                    PersonalDetailsScreenEffect.NavigateToFarmerDetailScreen(
                        firstName = firstName,
                        lastName = lastName,
                        streetName = streetName,
                        province = province,
                        county = county,
                        zipCode = zipCode
                    )
                )
            }
        }
    }*/

    override fun onBackButtonClicked() {
        sendNewEffect(PersonalDetailsScreenEffect.NavigateBack)
    }

    private fun tryCatch(block: () -> Unit) {
        try {
            block()
            clearErrors()
        } catch (e: AuthorizationException.InvalidFullNameException) {
            updateState { it.copy(isFullNameError = true) }
        } catch (e: AuthorizationException.InvalidAddressException) {
            updateState {
                it.copy(isAddressError = true)
            }
        }
    }

    private fun clearErrors() {
        updateState {
            it.copy(
                isFullNameError = false,
                isAddressError = false,
            )
        }
    }

}

