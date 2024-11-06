package com.freshtawi.tawi.presentation.screen.alerts

import com.freshtawi.tawi.domain.usecases.GetNotificationsUseCase
import com.freshtawi.tawi.domain.usecases.IManageAuthenticationUseCase
import com.freshtawi.tawi.domain.model.Notification
import com.freshtawi.tawi.presentation.common.base.BaseScreenModel
import com.freshtawi.tawi.domain.utils.ErrorState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class NotificationScreenModel(
    private val notificationManagement: GetNotificationsUseCase,
    private val manageAuthentication: IManageAuthenticationUseCase
) : BaseScreenModel<NotificationsUiState, NotificationUiEffect>(NotificationsUiState()),
    NotificationInteractionListener {



    init {
        checkIfLoggedIn()
        getNotifications()
    }

    private fun checkIfLoggedIn() {
        tryToExecute(
            { manageAuthentication.getAccessToken() },
            ::onCheckIfLoggedInSuccess,
            ::onCheckIfLoggedInError
        )
    }

    private fun onCheckIfLoggedInSuccess(accessToken: Flow<String>) {
        viewModelScope.launch {
            accessToken.collect { token ->
                if (token.isNotEmpty()) {
                    updateState { it.copy(isLoggedIn = true) }
                } else {
                    updateState { it.copy(isLoggedIn = false) }
                }
            }
        }
    }

    private fun onCheckIfLoggedInError(errorState: ErrorState) {
        updateState { it.copy(isLoggedIn = false) }
    }

    private fun getNotifications() {
        tryToExecute(
            { notificationManagement.getTodayNotifications() },
            ::onGetTodayNotificationsSuccess,
            ::onError
        )
        tryToExecute(
            { notificationManagement.getThisWeekNotifications() },
            ::onGetThisWeekNotificationsSuccess,
            ::onError
        )
    }

    override fun onClickTrackOrder() {
        TODO("Not yet implemented")
    }

    override fun onClickTryAgain() {
        TODO("Not yet implemented")
    }

    override fun onClickLogin() {
        sendNewEffect(NotificationUiEffect.NavigateToLoginScreen)
    }

    private fun onGetTodayNotificationsSuccess(todayNotifications: List<Notification>) {
        updateState { it.copy(todayNotifications = todayNotifications.toUiState()) }
    }

    private fun onGetThisWeekNotificationsSuccess(weekNotifications: List<Notification>) {
        updateState { it.copy(thisWeekNotifications = weekNotifications.toUiState()) }
    }

    private fun onError(error: ErrorState) {
        println("error is $error")
    }

}
