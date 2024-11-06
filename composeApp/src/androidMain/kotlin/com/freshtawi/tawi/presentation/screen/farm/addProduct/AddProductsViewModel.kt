package com.freshtawi.tawi.presentation.screen.farm.addProduct

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.freshtawi.tawi.presentation.screen.farm.addProduct.state.AddProductEvent
import com.freshtawi.tawi.presentation.screen.farm.addProduct.state.AddProductState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AddProductsViewModel : ViewModel() {

    val imageUri = mutableStateOf<Uri?>(null)

    private val _state = MutableStateFlow(AddProductState())
    val state: StateFlow<AddProductState> = _state.asStateFlow()

    private val _productImageUri = mutableStateOf<Uri?>(null)
    val productImageUri: State<Uri?> = _productImageUri
    fun setProductImageUri(value: Uri?) {
        _productImageUri.value = value
    }

    // Mutable state for the list of selected image URIs
    private val _selectedImageUris = mutableStateListOf<Uri>()
    val selectedImageUris: List<Uri>
        get() = _selectedImageUris

    // Function to add a selected image URI to the list
    fun addSelectedImageUri(uri: Uri) {
        _selectedImageUris.add(uri)
    }

    // Function to remove a selected image URI from the list
    fun removeSelectedImageUri(uri: Uri) {
        _selectedImageUris.remove(uri)
    }


    private val _mealImageUri = mutableStateOf<Uri?>(null)
    val mealImageUri: State<Uri?> = _mealImageUri
    fun setMealImageUri(value: Uri?) {
        _mealImageUri.value = value
    }

    fun setProductImage(imageUri: Uri) {
        this.imageUri.value = imageUri
    }

    fun onEvent(event: AddProductEvent) {
        when (event) {
            is AddProductEvent.OnProductNameChanged -> {
                _state.value = _state.value.copy(
                    productName = event.productName,
                    isProductNameValid = event.productName.isNotBlank()
                )
            }

            is AddProductEvent.OnProductDescriptionChanged -> {
                _state.value = _state.value.copy(
                    productDescription = event.productDescription,
                    isProductDescriptionValid = event.productDescription.isNotBlank()
                )
            }

            is AddProductEvent.OnProductPriceChanged -> {
                _state.value = _state.value.copy(
                    productPrice = event.productPrice,
                    isProductPriceValid = event.productPrice.isNotBlank()
                )
            }

            is AddProductEvent.OnProductQuantityChanged -> {
                _state.value = _state.value.copy(
                    productQuantity = event.productQuantity,
                    isProductQuantityValid = event.productQuantity.isNotBlank()
                )
            }

            is AddProductEvent.OnProductImageChanged -> {
                _state.value = _state.value.copy(
                    productImage = event.productImage,
                    isProductImageValid = event.productImage != null
                )
            }

            is AddProductEvent.OnProductCategoryChanged -> {
                _state.value = _state.value.copy(
                    productCategory = event.productCategory,
                    isProductCategoryValid = event.productCategory.isNotBlank()
                )
            }

            is AddProductEvent.OnProductTypeChanged -> {
                _state.value = _state.value.copy(
                    productType = event.productType,
                    isProductTypeValid = event.productType.isNotBlank()
                )
            }

            is AddProductEvent.OnProductLocationChanged -> {
                _state.value = _state.value.copy(
                    productLocation = event.productLocation,
                    isProductLocationValid = event.productLocation.isNotBlank()
                )
            }

            is AddProductEvent.OnProductDeliveryChanged -> {
                _state.value = _state.value.copy(
                    productDelivery = event.productDelivery,
                    isProductDeliveryValid = event.productDelivery.isNotBlank()
                )
            }

            is AddProductEvent.OnProductDeliveryPriceChanged -> {
                _state.value = _state.value.copy(
                    productDeliveryPrice = event.productDeliveryPrice,
                    isProductDeliveryPriceValid = event.productDeliveryPrice.isNotBlank()
                )
            }

            is AddProductEvent.OnProductDeliveryTimeChanged -> {
                _state.value = _state.value.copy(
                    productDeliveryTime = event.productDeliveryTime,
                    isProductDeliveryTimeValid = event.productDeliveryTime.isNotBlank()
                )
            }

            is AddProductEvent.OnNoOfTreesChange -> {
                _state.value = _state.value.copy(noOfTrees = event.noOfTrees)

            }
            is AddProductEvent.OnPhotosChange -> {
                _state.value = _state.value.copy(photos = event.photos)


            }
        }
    }
}

