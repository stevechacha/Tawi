package com.freshtawi.tawi.presentation.screen.auth.login

import com.freshtawi.tawi.presentation.common.base.BaseInteractionListener

interface LoginScreenInteractionListener : BaseInteractionListener {
    fun onUsernameChanged(username: String)
    fun onPasswordChanged(password: String)
    fun onKeepLoggedInChecked()
    fun onClickLogin(username: String, password: String, keepLoggedIn: Boolean)
    fun onClickSignUp()
}