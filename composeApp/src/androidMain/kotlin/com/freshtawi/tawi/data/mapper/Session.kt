package com.freshtawi.tawi.data.mapper

import com.freshtawi.tawi.data.network.response.SessionDto
import com.freshtawi.tawi.domain.model.Session


fun SessionDto.toSessionEntity() = Session(
    accessToken = accessToken,
    refreshToken = refreshToken,
    accessTokenExpirationDate = accessTokenExpirationDate,
    refreshTokenExpirationDate = refreshTokenExpirationDate,
)