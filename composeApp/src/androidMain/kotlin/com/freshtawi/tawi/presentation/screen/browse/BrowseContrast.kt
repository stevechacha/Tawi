package com.freshtawi.tawi.presentation.screen.browse

import androidx.annotation.StringRes
import com.freshtawi.tawi.R
import com.freshtawi.tawi.domain.model.Product
import com.freshtawi.tawi.domain.model.ProductAvailable


data class BrowseState(
    val products: List<Product> = emptyList(),
    val selectedCategory: String? = null,
    val productCategories: Set<String> = emptySet(),
    val selectedProduct: Product? = null,
    val isAddProductCartSheetOpen: Boolean = false,
    val isEditProductCartSheetOpen: Boolean = false,
    val isSelectedProductSheetOpen: Boolean = false,


    ) {
    val categories = products.map { it.category }.toSet()
    val selectedProducts = products.filter { it.category == selectedCategory }
    val harvestingSoonProducts = products.filter { it.isReadyToSell == ProductAvailable.Soon }

}

enum class BuyerPages(@StringRes val title: Int) {
    NEW_PRODUCTS(title = R.string.new_product),
    SHOP_LOCAL(title = R.string.shop_local),
    BUY_AGAIN(title = R.string.buy_again)
}

sealed interface BrowseEvent {
    object OnLoadProducts : BrowseEvent
    object OnLoadCategories : BrowseEvent
    object OnSelectCategory : BrowseEvent
    object OnSelectProduct : BrowseEvent
    object OnAddToCart : BrowseEvent
    object OnEditCart : BrowseEvent
    object LoadProducts : BrowseEvent
    object LoadCategories : BrowseEvent
    data class ProductLoaded(val products: List<Product>) : BrowseEvent
    data class OnSelectedProduct(val product: Product): BrowseEvent
    data class SelectProduct(val product: Product): BrowseEvent
    data class EditProductCart(val product: Product): BrowseEvent

    object SubmitProduct: BrowseEvent
    object OnAddNewProductCartClick: BrowseEvent
    object DismissProductCart: BrowseEvent


}



val productList = listOf(
    Product(
        id = 1,
        productName = "Mango",
        productPrice = 2.99,
        image = "https://upload.wikimedia.org/wikipedia/commons/9/90/Hapus_Mango.jpg",
        category = "New Product",
        estimatedDate = "Aug 15",
        location = "Farm 1",
        isReadyToSell = "Not Ready",
        variety = "Alphonso",
        farmNameHolder = "Farm 1",
        productDescription = "Fresh and juicy mangoes.",
        farmerProductAddress = "123 Mango St",
        farmerCounty = "Nairobi County",
        farmerProvince = "Nairobi",
        averageRating = 4.5,
        reviews = 100
    ),
    Product(
        id = 2,
        productName = "Mango",
        productPrice = 3.49,
        image = "https://upload.wikimedia.org/wikipedia/commons/9/90/Hapus_Mango.jpg",
        category = "Shop Local",
        estimatedDate = "Aug 20",
        location = "Farm 2",
        isReadyToSell = "Soon",
        variety = "Kent",
        farmNameHolder = "Farm 2",
        productDescription = "Premium quality mangoes.",
        farmerProductAddress = "456 Mango St",
        farmerCounty = "Mombasa County",
        farmerProvince = "Coast"
    ),
    Product(
        id = 3,
        productName = "Mango",
        productPrice = 2.75,
        image = "https://upload.wikimedia.org/wikipedia/commons/9/90/Hapus_Mango.jpg",
        category = "Buy Again",
        estimatedDate = "Aug 10",
        location = "Farm 3",
        isReadyToSell = "Ready",
        variety = "Ataulfo",
        farmNameHolder = "Farm 3",
        productDescription = "Delicious and sweet mangoes.",
        farmerProductAddress = "789 Mango St",
        farmerCounty = "Kisumu County",
        farmerProvince = "Western"
    ),
    Product(
        id = 4,
        productName = "Apple",
        productPrice = 1.99,
        image = "https://upload.wikimedia.org/wikipedia/commons/1/15/Red_Apple.jpg",
        category = "New Product",
        estimatedDate = "Aug 18",
        location = "Farm 4",
        isReadyToSell = "Ready",
        variety = "Fuji",
        farmNameHolder = "Green Fields Farm",
        productDescription = "Fresh and crispy apples.",
        farmerProductAddress = "456 Apple St",
        farmerCounty = "Nairobi County",
        farmerProvince = "Nairobi",
        averageRating = 4.2,
        reviews = 50
    ),
    Product(
        id = 5,
        productName = "Banana",
        productPrice = 0.99,
        image = "https://upload.wikimedia.org/wikipedia/commons/6/69/Banana.png",
        category = "Shop Local",
        estimatedDate = "Aug 22",
        location = "Farm 5",
        isReadyToSell = "Soon",
        variety = "Cavendish",
        farmNameHolder = "Yellow Orchard",
        productDescription = "Sweet and nutritious bananas.",
        farmerProductAddress = "789 Banana St",
        farmerCounty = "Mombasa County",
        farmerProvince = "Coast"
    ),
    Product(
        id = 6,
        productName = "Orange",
        productPrice = 1.49,
        image = "https://upload.wikimedia.org/wikipedia/commons/7/7b/Orange-Whole-%26-Split.jpg",
        category = "Buy Again",
        estimatedDate = "Aug 12",
        location = "Farm 6",
        isReadyToSell = "Not Ready",
        variety = "Valencia",
        farmNameHolder = "Sunshine Citrus",
        productDescription = "Juicy and refreshing oranges.",
        farmerProductAddress = "101 Orange St",
        farmerCounty = "Kisumu County",
        farmerProvince = "Western"
    ),
    // Add more products here
    Product(
        id = 7,
        productName = "Pineapple",
        productPrice = 2.99,
        image = "https://upload.wikimedia.org/wikipedia/commons/c/cb/Pineapple_and_cross_section.jpg",
        category = "New Product",
        estimatedDate = "Aug 25",
        location = "Farm 16",
        isReadyToSell = "Ready",
        variety = "Queen Victoria",
        farmNameHolder = "Tropical Delights",
        productDescription = "Fresh and tropical pineapples.",
        farmerProductAddress = "111 Pineapple St",
        farmerCounty = "Nakuru County",
        farmerProvince = "Rift Valley",
        averageRating = 4.5,
        reviews = 100
    ),
    Product(
        id = 8,
        productName = "Mango",
        productPrice = 2.75,
        image = "https://upload.wikimedia.org/wikipedia/commons/9/90/Hapus_Mango.jpg",
        category = "Buy Again",
        estimatedDate = "Aug 10",
        location = "Farm 3",
        isReadyToSell = "Not Ready",
        variety = "Ataulfo",
        farmNameHolder = "Farm 3",
        productDescription = "Description:\n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem ipsum... MORE",
        farmerProductAddress = "789 Mango St",
        farmerCounty = "Kisumu County",
        farmerProvince = "Western"
    ),
    Product(
        id = 9,
        productName = "Strawberry",
        productPrice = 3.49,
        image = "https://upload.wikimedia.org/wikipedia/commons/4/44/Strawberry_2009.jpg",
        category = "Shop Local",
        estimatedDate = "Aug 20",
        location = "Farm 2",
        isReadyToSell = "Soon",
        variety = "Kent",
        farmNameHolder = "Farm 2",
        productDescription = "Premium quality mangoes.",
        farmerProductAddress = "456 Mango St",
        farmerCounty = "Mombasa County",
        farmerProvince = "Coast"
    ),

)
