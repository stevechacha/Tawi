package com.freshtawi.tawi.domain.model

data class Farm(
    val farmId: Int,
    val farmName: String,
    val farmLocation: String,
    val product: List<ProductCrop>,
    val farmSizeAcres: Double,
    val farmerName: String,
    val phoneNumber: String,
    val farmImageUrl: String?= null
)
