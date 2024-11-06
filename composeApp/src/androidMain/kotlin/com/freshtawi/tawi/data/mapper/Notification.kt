package com.freshtawi.tawi.data.mapper

import com.freshtawi.tawi.data.network.response.NotificationDto
import com.freshtawi.tawi.domain.model.Notification


fun NotificationDto.toEntity(): Notification {
    return Notification(
        id = id ?: "",
        title = title,
        body = body,
        date = date.toDate(),
        time = date.toTime(),
        userId = userId ?: "",
        topic = topic ?: "",
    )
}