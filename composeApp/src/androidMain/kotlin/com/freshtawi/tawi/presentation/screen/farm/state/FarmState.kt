package com.freshtawi.tawi.presentation.screen.farm.state

import com.freshtawi.tawi.domain.model.Farm


data class FarmState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val streetName: String ="",
    val confirmPassword: String = "",
    val zipCode: String = "",
    val province: String = "",
    val county: String = "",
    val location: String = "",
    val farmSize: String = "",
    val farmList: List<Farm> = emptyList(),
    val cropTypeList : List<String> = emptyList()
)
