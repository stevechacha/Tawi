package com.freshtawi.tawi.data.repo

import com.freshtawi.tawi.domain.model.Notification
import com.freshtawi.tawi.domain.repo.INotificationGateway


class NotificationGateway : INotificationGateway {
    override suspend fun getNotificationHistory(): List<Notification> {
        TODO("Not yet implemented")
    }
}