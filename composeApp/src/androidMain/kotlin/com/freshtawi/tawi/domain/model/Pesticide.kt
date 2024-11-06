package com.freshtawi.tawi.domain.model

import java.time.LocalDate

data class Pesticide(
    val pesticideName: String,
    val dateSprayed: LocalDate,
    val pesticideImage: Int? = null,
    val productCrop: ProductCrop
)