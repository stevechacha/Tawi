package com.freshtawi.tawi.presentation.screen.auth.registration.tabs.loginDetail

import com.freshtawi.tawi.presentation.common.base.BaseInteractionListener

interface LoginDetailsInteractionListener : BaseInteractionListener {
    fun onEmailChanged(email: String)
    fun onPasswordChanged(password: String)
    fun onConfirmPasswordChanged(confirmPassword: String)
    fun onPhoneChanged(phone: String)
    fun onSignUpButtonClicked()
    fun onBackButtonClicked()
}