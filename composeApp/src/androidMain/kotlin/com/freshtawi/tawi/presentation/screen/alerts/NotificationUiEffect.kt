package com.freshtawi.tawi.presentation.screen.alerts

sealed class NotificationUiEffect {
    data object NavigateToTraceOrderScreen : NotificationUiEffect()
    data object MakeOrderAgain : NotificationUiEffect()
    data object NavigateToLoginScreen : NotificationUiEffect()
}
