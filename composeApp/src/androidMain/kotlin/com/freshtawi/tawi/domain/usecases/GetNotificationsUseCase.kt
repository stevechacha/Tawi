package com.freshtawi.tawi.domain.usecases

import com.freshtawi.tawi.domain.model.Notification
import com.freshtawi.tawi.domain.repo.INotificationGateway


interface IGetNotificationsUseCase {
    suspend fun getTodayNotifications(): List<Notification>
    suspend fun getThisWeekNotifications(): List<Notification>
}

class GetNotificationsUseCase(
    private val notificationGateway: INotificationGateway
) : IGetNotificationsUseCase {
    override suspend fun getTodayNotifications(): List<Notification> {
        return notificationGateway.getNotificationHistory().take(2)
    }

    override suspend fun getThisWeekNotifications(): List<Notification> {
        return notificationGateway.getNotificationHistory().takeLast(3)
    }
}
