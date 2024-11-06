package com.freshtawi.tawi.presentation.screen.alerts

import com.freshtawi.tawi.domain.model.Notification
import com.freshtawi.tawi.domain.model.Time

data class NotificationsUiState(
    val todayNotifications: List<NotificationUiState> = emptyList(),
    val thisWeekNotifications: List<NotificationUiState> = emptyList(),
    val isLoggedIn: Boolean = true,
)

data class NotificationUiState(
    val title: String = "",
    val body: String = "",
    val isClickable: Boolean = false,
    val clickableText: String = "",
    val time: Time = Time(0, 0),
)

fun Notification.toUiState(): NotificationUiState {
    return NotificationUiState(
        title = title,
        body = body,
        time = time
    )
}

fun List<Notification>.toUiState(): List<NotificationUiState> {
    return this.map { it.toUiState() }
}