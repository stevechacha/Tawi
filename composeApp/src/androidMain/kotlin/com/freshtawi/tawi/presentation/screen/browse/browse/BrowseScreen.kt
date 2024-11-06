package com.freshtawi.tawi.presentation.screen.browse.browse

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.freshtawi.tawi.presentation.screen.browse.browse.component.BrowseProductItem
import com.freshtawi.tawi.presentation.screen.browse.browse.component.ProductHarvestSoonListItem
import com.freshtawi.tawi.presentation.common.composable.BackgroundContainer
import com.freshtawi.tawi.domain.model.Product


@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BrowseScreen(
    navigateToProductDetail:(Int) -> Unit = {},
    browseViewModel: BrowseViewModel = viewModel()
) {
    val browState by browseViewModel.browseState.collectAsState()
    val list = remember { mutableStateListOf<Product>() }

    LaunchedEffect(key1 = Unit) {
        list.shuffle()
        list.add(browState.products.last())
    }

    BackgroundContainer(
        topBar = {
            BrowseTopBar(
                title = "Browse",
                initialValue = "",
                onSearchParamChange = {},
                showSearchBar = true,
                showBackArrow = false,
                showMenuBar = true
            )
        }
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                HarvestingSoonProduct(navigateToProductDetail = navigateToProductDetail)
            }
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Browse Menu",
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        for (category in browState.categories) {
                            val isSelected = category == browState.selectedCategory
                            Text(
                                text = category,
                                fontSize = 12.sp,
                                lineHeight = 18.sp,
                                fontWeight = FontWeight.W400,
                                textDecoration = if (isSelected) TextDecoration.Underline else TextDecoration.None,
                                modifier = Modifier
                                    .clickable {
                                        browseViewModel.selectCategory(category)
                                    },
                                color = if (isSelected) Color.Black else Color.Gray
                            )
                        }
                    }
                }

            }
            val selectedProductItems = browState.selectedProducts
            items(selectedProductItems) { product ->
                AnimatedVisibility(
                    visible = browState.selectedProducts.isNotEmpty(),
                    enter = slideInVertically(
                        animationSpec = tween(durationMillis = 300),
                        initialOffsetY = { it }
                    ),
                    exit = slideOutVertically(
                        animationSpec = tween(durationMillis = 300),
                        targetOffsetY = { it }
                    ),
                ) {
                    BrowseProductItem(
                        product = product,
                        onProductItem = { navigateToProductDetail(it.id) },
                        modifier = Modifier.animateItemPlacement(
                            animationSpec = tween(
                                durationMillis = 1000
                            )
                        )
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HarvestingSoonProduct(
    navigateToProductDetail: (Int) -> Unit,
    browseViewModel: BrowseViewModel = viewModel()
) {
    val browseState by browseViewModel.browseState.collectAsState()
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Harvesting Soon",
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.W400,
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(
                browseState.harvestingSoonProducts,
            ) { product ->
                ProductHarvestSoonListItem(
                    product = product,
                    onItemClick = { navigateToProductDetail(it.id)},
                    modifier = Modifier.animateItemPlacement()
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun PreviewBrowseScreen(modifier: Modifier = Modifier) {
    BrowseScreen()
}





