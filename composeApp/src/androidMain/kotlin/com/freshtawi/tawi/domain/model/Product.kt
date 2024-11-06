package com.freshtawi.tawi.domain.model


data class Product(
    val id: Int = 0,
    val productName: String,
    val productPrice: Double,
    val isProductAvailable: Boolean = false,
    val productPickupDate: String = "",
    val productQuantity: Int = 6,
    val productDescription: String,
    val category: String,
    val averageRating: Double = 0.0,
    val reviews: Int = 0,
    val image: String = "",
    val imageUrl: String = "",
    val brand: String = "",
    val estimatedDate: String,
    val location: String,
    val isReadyToSell: String,
    val variety: String,
    val farmNameHolder: String,
    val farmerProductAddress: String,
    val farmerCounty: String,
    val farmerProvince: String,
    val totalAmount: Double = 0.0,
    val subTotalPerProduct: Double = 0.0,
    val farmerDistance: Double = 0.0

) {
    val totalSub = productQuantity * productPrice
    val allTotals  = productQuantity * productPrice * 11
}

object ProductAvailable {
    const val Ready = "Ready"
    const val Soon = "Soon"
    const val NotReady = "Not Ready" // Using spaces in the string value
}



