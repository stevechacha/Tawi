package com.freshtawi.tawi.presentation.screen.browse.productCart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.freshtawi.tawi.presentation.screen.browse.productDetail.component.CustomTab
import com.freshtawi.tawi.presentation.screen.browse.browse.BrowseViewModel
import com.freshtawi.tawi.presentation.screen.browse.modalSheetLayout.BrowseBottomSheetType
import com.freshtawi.tawi.presentation.screen.browse.productList
import com.freshtawi.tawi.presentation.common.composable.AppButton

@Composable
fun ProductCartScreen(
    browseViewModel: BrowseViewModel = viewModel(),
    id: Int,
    navigateBack: ()-> Unit,
) {

    val browseState by browseViewModel.browseState.collectAsState()
    val interactionSource = remember { MutableInteractionSource() }
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    var currentBottomSheet: BrowseBottomSheetType? by remember { mutableStateOf(null) }
    val coroutineScope = rememberCoroutineScope()
    val product = productList.find { it.id == id }!!
    val subTotalAmount = product.productPrice * product.productQuantity

    Scaffold(
        topBar = {
            ProductCartTopBar(
                navigateBack = navigateBack
            )
        },
        bottomBar = {
            ProductSubmit(
                subTotalAmount = subTotalAmount.toString(),
                onclick = {}
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "EDIT",
                        textDecoration = TextDecoration.Underline,
                        textAlign = TextAlign.End,
                        modifier = Modifier.clickable(interactionSource, null) {
                            isSheetOpen = true
                            currentBottomSheet = BrowseBottomSheetType.EDIT_ITEM_CART
                        }
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth().height(3.dp)
                        .background(
                            color = Color.Black,
                            shape = RoundedCornerShape(6.dp)
                        )
                )

            }
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                item {
                    Spacer(modifier = Modifier.height(12.dp))
                }

                item {
                    Column {
                        ProductCartItem(
                            product = product,
                            onProductItemClick = {
                                currentBottomSheet = BrowseBottomSheetType.EDIT_ITEM_CART
                                isSheetOpen = true
                            },
                        )

                    }
                }

            }
        }

        /*if (isSheetOpen) {
            VunaModalSheet(
                onDismissRequest = {
                    isSheetOpen = false
                },
            ) {
                currentBottomSheet?.let {
                    BrowseModalSheetLayout(
                        bottomSheetType = it,
                        onClose = {},
                    )
                }
            }
        }*/

    }

}


@Composable
fun ProductSubmit(
    onclick:()->Unit = {},
    subTotalAmount: String
) {
    val (selected, setSelected) = remember { mutableIntStateOf(0) }
    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Sub Total:",
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.W700,
                )
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "$ $subTotalAmount",
                        textAlign = TextAlign.End,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.W700,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "(Taxes not included)",
                        textAlign = TextAlign.End,
                        fontSize = 10.sp,
                        lineHeight = 15.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            CustomTab(
                items = listOf("Tonnes", "Counts"),
                selectedItemIndex = selected,
                onClick = setSelected,
            )

        }

        AppButton(
            text = "CHECKOUT",
            onClick = onclick,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
        )
    }

}





