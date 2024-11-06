package com.freshtawi.tawi.presentation.screen.auth.registration

import com.freshtawi.tawi.domain.utils.AuthorizationException
import com.freshtawi.tawi.domain.usecases.IManageAuthenticationUseCase
import com.freshtawi.tawi.domain.usecases.IManageSettingUseCase
import com.freshtawi.tawi.domain.usecases.validation.IValidationUseCase
import com.freshtawi.tawi.domain.model.Account
import com.freshtawi.tawi.presentation.common.base.BaseScreenModel
import com.freshtawi.tawi.domain.utils.ErrorState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegistrationViewModel (
    private val validation: IValidationUseCase,
    private val manageAuthentication: IManageAuthenticationUseCase,
    private val manageUser: IManageSettingUseCase,
): BaseScreenModel<RegistrationScreenUiState, RegistrationScreenUiEffect>(
    RegistrationScreenUiState()
),
    RegistrationScreenInteractionListener {
    override fun setCompanyName(companyName: String) {
        updateState { it.copy( buyerCompanyName =  companyName) }
    }

    override fun setBuyerType(buyerType: String) {
        updateState { it.copy( buyerType = buyerType) }
    }

    override fun setBuyerReceiveProductModel(buyerReceiveProductModel: String) {
        updateState { it.copy( buyerReceiveProductModel = buyerReceiveProductModel) }
    }

    override fun setFarmerProductName(farmerProduct: String) {
        updateState { it.copy( farmerProductName =  farmerProduct) }
    }

    override fun setFarmerShipProductModel(farmerShipProductModel: String) {
       updateState { it.copy( farmerShipProductModel = farmerShipProductModel) }
    }

    override fun onEmailChanged(email: String) {
        updateState { it.copy( email = email) }
    }

    override fun onPasswordChanged(password: String) {
        updateState { it.copy( password = password) }
    }

    override fun onConfirmPasswordChanged(confirmPassword: String) {
        updateState { it.copy( confirmPassword = confirmPassword) }
    }

    override fun onPhoneChanged(phone: String) {
        updateState { it.copy( phone = phone) }
    }

    override fun onSignUpButtonClicked() {
        with(state.value) {
            tryToExecute(
                function = {
                    manageAuthentication.createUser(
                        Account(
                            "fullName",
                            username,
                            password,
                            email,
                            phone,
                            address
                        )
                    )
                },
                onSuccess = ::onRegistrationSuccess,
                onError = ::onError
            )
        }
    }

    private fun onRegistrationSuccess(success: Boolean) {
        sendNewEffect(RegistrationScreenUiEffect.OnGoToPreferredLanguage)
    }

    private fun onError(error: ErrorState) {
        clearErrors()
        when (error) {
            ErrorState.InvalidPhone -> updateState { it.copy(isPhoneError = true) }
            is ErrorState.UserAlreadyExists -> showSnackbar(error.message)
            else -> {}
        }
    }

    override fun onFirstNameChanged(firstName: String) {
        updateState { it.copy( firstName = firstName) }
    }

    override fun onLastNameChanged(lastName: String) {
        updateState { it.copy( lastName = lastName) }
    }

    override fun onStreetInformationChanged(streetName: String) {
        updateState { it.copy( streetName = streetName) }
    }

    override fun onProvinceChanged(province: String) {
        updateState { it.copy( province = province) }
    }

    override fun onCountyChanged(county: String) {
        updateState { it.copy( county = county) }
    }

    override fun onZipCodeChanged(zipCode: String) {
        updateState { it.copy( zipCode = zipCode) }
    }
    override fun onFarmerSelected(farmer: String){
        updateState{ it.copy(farmer = farmer) }
    }

    override fun onBuyerSelected(buyer: String){
        updateState { it.copy(buyer = buyer) }
    }

    override fun onContinueButtonClicked(userType: String) {

    }

    override fun onBackButtonClicked() {

    }

    private fun tryCatch(block: () -> Unit) {
        try {
            block()
            clearErrors()
        } catch (e: AuthorizationException.InvalidPhoneException) {
            updateState {
                it.copy(isPhoneError = true)
            }
        }  catch (e: AuthorizationException.InvalidEmailException) {
            updateState {
                it.copy(isEmailError = true)
            }
        } catch (e: AuthorizationException.InvalidPasswordException) {
            updateState { it.copy(isPasswordError = true) }
        }
    }

    private fun clearErrors() {
        updateState {
            it.copy(
                isPhoneError = false,
                isPasswordError = false,
                isEmailError = false,
                isLoading = false
            )
        }
    }
    private fun showSnackbar(message: String) {
        viewModelScope.launch {
            updateState { it.copy(snackbarMessage = message, showSnackbar = true) }
            delay(2000) // wait for snackbar to show
            updateState { it.copy(showSnackbar = false) }
            delay(300) // wait for snackbar to hide
            updateState { it.copy(snackbarMessage = "") }
        }
    }
}