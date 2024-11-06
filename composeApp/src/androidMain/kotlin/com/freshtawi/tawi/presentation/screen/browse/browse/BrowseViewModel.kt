package com.freshtawi.tawi.presentation.screen.browse.browse

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freshtawi.tawi.presentation.screen.browse.BrowseEvent
import com.freshtawi.tawi.presentation.screen.browse.BrowseState
import com.freshtawi.tawi.presentation.screen.browse.productList
import com.freshtawi.tawi.domain.model.Product
import com.freshtawi.tawi.domain.model.ProductAvailable
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class BrowseViewModel : ViewModel() {
    private val _browseState = MutableStateFlow(BrowseState())
    val browseState = _browseState.asStateFlow()

    var newProduct: Product? by mutableStateOf(null)
        private set

    init {
        loadProducts()
        loadCategories()
        selectDefaultCategory()
    }

    fun onEvent(event: BrowseEvent) {
        when (event) {
            BrowseEvent.OnAddToCart -> {}
            BrowseEvent.OnEditCart -> {}
            BrowseEvent.OnLoadCategories -> {
                viewModelScope.launch {
                    loadCategories()
                }
            }

            BrowseEvent.OnLoadProducts -> {
                viewModelScope.launch {
                    loadProducts()
                }
            }

            BrowseEvent.OnSelectCategory -> {}
            BrowseEvent.OnSelectProduct -> {

            }

            BrowseEvent.LoadCategories -> {}
            BrowseEvent.LoadProducts -> {}
            is BrowseEvent.ProductLoaded -> {
                _browseState.update { state ->
                    state.copy(products = event.products)
                }
            }

            is BrowseEvent.OnSelectedProduct -> {
                _browseState.update {
                    it.copy(
                        selectedProduct = event.product,
                        isSelectedProductSheetOpen = true
                    )
                }
            }

            BrowseEvent.DismissProductCart -> {
                viewModelScope.launch {
                    _browseState.update {
                        it.copy(
                            isSelectedProductSheetOpen = false,
                            isAddProductCartSheetOpen = false
                        )
                    }
                    delay(300L)
                    newProduct = null
                    _browseState.update {
                        it.copy(
                            selectedProduct = null
                        )
                    }
                }

            }

            is BrowseEvent.EditProductCart -> {
                _browseState.update {
                    it.copy(
                        selectedProduct = null,
                        isAddProductCartSheetOpen = true,
                        isSelectedProductSheetOpen = false
                    )
                }

            }

            BrowseEvent.OnAddNewProductCartClick -> {
                _browseState.update {
                    it.copy(
                        isAddProductCartSheetOpen = true
                    )
                }

                newProduct = Product(
                    id = 0,
                    productName = "",
                    productPrice = 0.00,
                    image = 0.toString(),
                    category = "String",
                    estimatedDate = "String",
                    location = "String",
                    isReadyToSell = "String",
                    variety = "String",
                    farmNameHolder = "String",
                    productQuantity = 6,
                    productDescription = "String",
                    farmerProductAddress = "String",
                    farmerCounty = "String",
                    farmerProvince = "String",
                    productPickupDate = "",
                    totalAmount = 0.0,
                    subTotalPerProduct = 0.0
                )

            }

            is BrowseEvent.SelectProduct -> {
                _browseState.update {
                    it.copy(
                        selectedProduct = event.product,
                        isSelectedProductSheetOpen = true
                    )
                }

            }

            BrowseEvent.SubmitProduct -> {

            }
        }
    }

    private fun loadProducts() {
        // Simulate loading products from a data source. Replace this with your actual data loading logic.
        val products = productList
        _browseState.value = _browseState.value.copy(products = products)
    }

    private fun loadCategories() {
        _browseState.update { state ->
            state.copy(productCategories = state.products.map { it.category }.toSet())
        }
    }

    fun selectCategory(category: String) {
        _browseState.update { state ->
            state.copy(selectedCategory = category)
        }
    }

    fun getHarvestingSoonProducts(): List<Product> {
        return _browseState.value.products.filter { it.isReadyToSell == ProductAvailable.Soon }
    }

    private fun selectDefaultCategory() {
        _browseState.update { state ->
            if (state.selectedCategory == null && state.categories.isNotEmpty()) {
                state.copy(selectedCategory = state.categories.first())
            } else {
                state
            }
        }
    }
}

