package com.freshtawi.tawi.presentation.screen.auth.registration.tabs.loginDetail

import com.freshtawi.tawi.domain.model.Account
import com.freshtawi.tawi.domain.usecases.IManageAuthenticationUseCase
import com.freshtawi.tawi.domain.usecases.IManageSettingUseCase
import com.freshtawi.tawi.domain.usecases.validation.IValidationUseCase
import com.freshtawi.tawi.presentation.common.base.BaseScreenModel
import com.freshtawi.tawi.domain.utils.ErrorState
import com.freshtawi.tawi.domain.utils.AuthorizationException
import com.freshtawi.tawi.presentation.common.resources.util.LanguageCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginDetailsViewModel (
    private val validation: IValidationUseCase,
    private val manageAuthentication: IManageAuthenticationUseCase,
    private val manageUser: IManageSettingUseCase,
) : BaseScreenModel<LoginDetailUiState, LoginDetailsScreenEffect>(
    LoginDetailUiState()
), LoginDetailsInteractionListener {
    private val accountList = mutableListOf<Account>()
    private val _language: MutableStateFlow<LanguageCode> = MutableStateFlow(LanguageCode.EN)
    val language: StateFlow<LanguageCode> = _language.asStateFlow()

    private fun getUserLanguageCode() {
        viewModelScope.launch(Dispatchers.IO) {
            manageUser.getUserLanguageCode().distinctUntilChanged().collectLatest { lang ->
                _language.update {
                    LanguageCode.entries.find { languageCode -> languageCode.value == lang }
                        ?: LanguageCode.EN
                }
            }
        }
    }
    init {
        getUserLanguageCode()
    }

    fun getUser(userType: String): Account {
        return accountList.find { it.id == userType }?: Account()
    }
    override fun onEmailChanged(email: String) {
        updateState { it.copy(email = email) }
    }

    fun setUser(account: Account){
        accountList.add(account)
        updateState { it.copy(fullName = account.fullName) }
        updateState { it.copy(username = account.username) }
        updateState { it.copy(password = account.password) }
        updateState { it.copy(phone = account.phone) }
        updateState { it.copy(address = account.address) }
    }

    override fun onPasswordChanged(password: String) {
        updateState { it.copy(password = password) }
    }

    override fun onConfirmPasswordChanged(confirmPassword: String) {
        updateState { it.copy(confirmPassword = confirmPassword) }
    }

    override fun onPhoneChanged(phone: String) {
        updateState { it.copy(phone = phone) }
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
        sendNewEffect(LoginDetailsScreenEffect.NavigateToLoginScreen)
    }
    override fun onBackButtonClicked() {
        sendNewEffect(LoginDetailsScreenEffect.NavigateBack)
    }

    private fun onError(error: ErrorState) {
        clearErrors()
        when (error) {
            ErrorState.InvalidPhone -> updateState { it.copy(isPhoneError = true) }
            is ErrorState.UserAlreadyExists -> showSnackbar(error.message)
            else -> {}
        }
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