package com.freshtawi.tawi.domain.model

data class Account(
    var id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val streetName: String = "",
    val zipCode: String = "",
    val province: String = "",
    val county: String = "",
    val fullName: String = "",
    val username: String = "",
    val password: String = "",
    val email: String = "",
    val phone: String = "",
    val address: String = "",
    val farmerProductName: String = "",
    val farmerShipMethod: String = "",
    val buyerCompanyName: String = "",
    val buyerShipMethod: String = "",
    val buyerType: String = ""
)
