package com.freshtawi.tawi.data.mapper

import com.freshtawi.tawi.data.network.response.AddressDto
import com.freshtawi.tawi.domain.model.Address

fun AddressDto.toEntity(): Address {
    return Address(
        id = id ?: "",
        location = location?.toEntity(),
        address = address ?: "",
    )
}
