package com.freshtawi.tawi.domain.repo

import com.freshtawi.tawi.domain.model.Notification

interface INotificationGateway {
    suspend fun getNotificationHistory(): List<Notification>

}