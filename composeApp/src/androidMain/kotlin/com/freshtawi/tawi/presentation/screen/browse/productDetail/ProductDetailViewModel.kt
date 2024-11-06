package com.freshtawi.tawi.presentation.screen.browse.productDetail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProductDetailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ProductUI())
    val uiState = _uiState.asStateFlow()

    val isLoading = MutableStateFlow(false)
    val message = MutableSharedFlow<String>()

    fun setQuantity(productQuantity: String){
        _uiState.update { it.copy( productQuantity = productQuantity) }
    }

    fun updateChosenDate(chosenDate: String){
        _uiState.update { it.copy( productPickupDate = chosenDate) }
    }

}