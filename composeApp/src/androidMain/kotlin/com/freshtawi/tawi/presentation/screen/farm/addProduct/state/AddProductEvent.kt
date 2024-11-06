package com.freshtawi.tawi.presentation.screen.farm.addProduct.state

import android.net.Uri


sealed class AddProductEvent {
    data class OnProductNameChanged(val productName: String) : AddProductEvent()
    data class OnProductDescriptionChanged(val productDescription: String) : AddProductEvent()
    data class OnProductPriceChanged(val productPrice: String) : AddProductEvent()
    data class OnProductQuantityChanged(val productQuantity: String) : AddProductEvent()
    data class OnProductImageChanged(val productImage: Uri?) : AddProductEvent()
    data class OnProductCategoryChanged(val productCategory: String) : AddProductEvent()
    data class OnProductTypeChanged(val productType: String) : AddProductEvent()
    data class OnProductLocationChanged(val productLocation: String) : AddProductEvent()
    data class OnProductDeliveryChanged(val productDelivery: String) : AddProductEvent()
    data class OnProductDeliveryPriceChanged(val productDeliveryPrice: String) : AddProductEvent()
    data class OnProductDeliveryTimeChanged(val productDeliveryTime: String) : AddProductEvent()
    data class OnNoOfTreesChange(val noOfTrees: String): AddProductEvent()
    data class OnPhotosChange(val photos: List<String>): AddProductEvent()


}
