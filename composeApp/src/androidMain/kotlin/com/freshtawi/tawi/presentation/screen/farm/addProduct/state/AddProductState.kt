package com.freshtawi.tawi.presentation.screen.farm.addProduct.state

import android.net.Uri


data class AddProductState(
    val productName: String = "",
    val isProductNameValid: Boolean = true,
    val productDescription: String = "",
    val isProductDescriptionValid: Boolean = true,
    val productPrice: String = "",
    val isProductPriceValid: Boolean = true,
    val productQuantity: String = "",
    val isProductQuantityValid: Boolean = true,
    val productImage: Uri? = null,
    val isProductImageValid: Boolean = true,
    val productCategory: String = "",
    val noOfTrees: String = "",
    val isProductCategoryValid: Boolean = true,
    val productType: String = "",
    val isProductTypeValid: Boolean = true,
    val productLocation: String = "",
    val isProductLocationValid: Boolean = true,
    val productDelivery: String = "",
    val isProductDeliveryValid: Boolean = true,
    val productDeliveryPrice: String = "",
    val isProductDeliveryPriceValid: Boolean = true,
    val productDeliveryTime: String = "",
    val photos: List<String> = emptyList(),
    val isProductDeliveryTimeValid: Boolean = true,
    val isLoading: Boolean = false,
    val isProductAdded: Boolean = false,
    val isProductAddedError: Boolean = false,
    val isProductAddedErrorMessage: String = "",
    val isProductAddedSuccessMessage: String = "",
    val isProductAddedErrorType: String = "",
    val isProductAddedErrorTitle: String = "",
    val isProductAddedErrorDescription: String = "",
    val isProductAddedErrorIcon: String = "",
    val isProductAddedErrorIconColor: String = "",
    val isProductAddedErrorButton: String = "",
    val isProductAddedErrorButtonColor: String = "",
    val isProductAddedErrorButtonTextColor: String = "",
    val isProductAddedErrorButtonLink: String = "",
    val isProductAddedErrorButtonLinkColor: String = "",
    val isProductAddedErrorButtonLinkTextColor: String = "",
    val isProductAddedErrorButtonLinkText: String = "",
    val isProductAddedErrorButtonLinkTextIcon: String = "",
    val isProductAddedErrorButtonLinkTextIconColor: String = "",
    val isProductAddedErrorButtonLinkTextIconLink: String = "",
    val isProductAddedErrorButtonLinkTextIconLinkColor: String = "",
    val isProductAddedErrorButtonLinkTextIconLinkText: String = "",
    val isProductAddedErrorButtonLinkTextIconLinkTextColor: String = "",
)

data class AddProductsState(
    val name: String = "",
    val description: String = "",
    val price: String = "",
    val isLoading: Boolean = false,
    val isNameValid: Boolean = false,
    val isDescriptionValid: Boolean = false,
    val isPriceValid: Boolean = false,
    val isAddProductButtonEnabled: Boolean = false,
    val isAddProductError: Boolean = false,
    val isAddProductSuccess: Boolean = false,
    val isAddProductFailure: Boolean = false,
    val isAddProductButtonClicked: Boolean = false,
)


data class Product(
    val name: String = "",
    val description: String = "",
    val type: String = "",
    val noOfTrees: Int = 0,
    val photos: List<String> = emptyList()
)

data class ProductState(
    val product: Product = Product()
)
