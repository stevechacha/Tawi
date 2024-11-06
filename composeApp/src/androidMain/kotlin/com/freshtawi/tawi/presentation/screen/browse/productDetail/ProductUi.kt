package com.freshtawi.tawi.presentation.screen.browse.productDetail

data class ProductUI(
    val id: Int = 0,
    val productName: String = "",
    val price: Double = 0.0,
    val image: Int = 0,
    val category: String = "",
    val estimatedDate: String= "",
    val location: String = "",
    val isReadyToSell: String = "",
    val variety: String = "",
    val farmNameHolder: String = "",
    val productQuantity: String = "",
    val productDescription: String = "",
    val farmerProductAddress: String ="",
    val farmerCounty: String ="",
    val farmerProvince: String= "",
    val productPickupDate: String = "",
    val totalAmount: Double = 0.0,
    val subTotalPerProduct: Double = 0.0
)
