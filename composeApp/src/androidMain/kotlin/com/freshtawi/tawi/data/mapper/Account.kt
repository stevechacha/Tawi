package com.freshtawi.tawi.data.mapper

import com.freshtawi.tawi.data.network.response.UserRegistrationDto
import com.freshtawi.tawi.domain.model.Account


fun Account.toUserRegistrationDto(): UserRegistrationDto {
    return UserRegistrationDto(
        fullName = fullName,
        username = username,
        password = password,
        email = email,
        phone = phone,
        address = address
    )
}