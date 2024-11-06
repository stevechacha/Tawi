package com.freshtawi.tawi.data.mapper

import com.freshtawi.tawi.data.network.response.UserDetailsDto
import com.freshtawi.tawi.domain.model.Price
import com.freshtawi.tawi.domain.model.User


fun UserDetailsDto.toEntity() = User(
    id = id ?: "",
    country = fullName ?: "",
    email = email ?: "",
    fullName = fullName ?: "",
    permission = permission ?: 1,
    username = username ?: "",
    wallet = Price(walletBalance ?: 0.0, currency ?: ""),
    addresses = addresses?.filterNotNull()?.map { it.toEntity() } ?: emptyList(),
    phoneNumber = phoneNumber ?: ""
)



