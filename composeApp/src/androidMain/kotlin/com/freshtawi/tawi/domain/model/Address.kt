package com.freshtawi.tawi.domain.model

data class Address(
    val id: String,
    val address: String,
    val location: Location? = null,
)
