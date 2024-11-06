package com.freshtawi.tawi.domain.model

data class Session(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpirationDate: Long,
    val refreshTokenExpirationDate: Long,
)