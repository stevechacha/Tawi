package com.freshtawi.tawi.presentation.screen.browse.productDetail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.freshtawi.tawi.presentation.screen.browse.productDetail.component.AnimatedCardPagerIndicator
import com.freshtawi.tawi.presentation.screen.browse.productDetail.component.CustomTab
import com.freshtawi.tawi.presentation.screen.browse.productDetail.component.ProductDetailAddressItem
import com.freshtawi.tawi.presentation.screen.browse.productDetail.component.ProductDetailListContent
import com.freshtawi.tawi.presentation.screen.browse.productDetail.component.ProductDetailTopBar
import com.freshtawi.tawi.presentation.screen.browse.productDetail.component.ProductReviewRatingItem
import com.freshtawi.tawi.presentation.screen.browse.modalSheetLayout.BrowseBottomSheetType
import com.freshtawi.tawi.presentation.screen.browse.modalSheetLayout.BrowseModalSheetLayout
import com.freshtawi.tawi.presentation.screen.browse.productDetail.component.AddToCartButton
import com.freshtawi.tawi.presentation.screen.browse.productList
import com.freshtawi.tawi.presentation.common.composable.VunaModalSheet
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.ColorBorder


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    id: Int,
    navigateBack: ()->Unit,
    navigateToCart:(Int)->Unit = {},
    productDetailViewModel: ProductDetailViewModel = viewModel()
) {
    val productDetailUiState by productDetailViewModel.uiState.collectAsState()
    val (selected, setSelected) = remember { mutableIntStateOf(0) }
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    var currentBottomSheet: BrowseBottomSheetType? by remember { mutableStateOf(null) }
    val coroutineScope = rememberCoroutineScope()
    val product = productList.find { it.id == id }!!

    Scaffold(
        topBar = {
            ProductDetailTopBar(
                title = "Product Details",
                onBackClick = { navigateBack() },
                onClickActionButton = {},
                showBackArrow = false
            )
        },
    ) { PaddingValues ->
        Column(
            modifier = Modifier
                .padding(PaddingValues)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = product.productName,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(200.dp)
                    .border(
                        1.dp, MaterialTheme.colorScheme.onBackground,
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                AnimatedCardPagerIndicator(
                    products = listOf(product.takeIf { it.id == id } ?: product)
                )
            }
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProductDetailListContent(product = product)
                HorizontalDivider(
                    Modifier.fillMaxWidth().height(1.dp),
                    color = ColorBorder
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ProductReviewRatingItem(product = product)
                    ProductDetailAddressItem(product = product)
                    CustomTab(
                        items = listOf("Tonnes", "Counts"),
                        selectedItemIndex = selected,
                        onClick = setSelected
                    )
                }
            }
            AddToCartButton(
                productQuantity = productDetailUiState.productQuantity.toString(),
                onProductQuantityChanged = productDetailViewModel::setQuantity,
                buttonText = "Add to Cart",
                hint = "Tonnes to purchase",
                modifier = Modifier.padding(horizontal = 16.dp),
                onClick = {
                    currentBottomSheet = BrowseBottomSheetType.ADD_TO_CART
                    isSheetOpen = true
                },
            )
        }
    }

    if (isSheetOpen) {
        VunaModalSheet(
            onDismissRequest = { isSheetOpen = false },
        ) {
            currentBottomSheet?.let {
                BrowseModalSheetLayout(
                    bottomSheetType = it,
                    onClose = { isSheetOpen = false },
                    isSheetOpen = isSheetOpen,
                    product = product,
                    chosenDate = productDetailUiState.productPickupDate,
                    onDateChanged = productDetailViewModel::updateChosenDate,
                    navigateToCart = navigateToCart
                )
            }
        }
    }

}







