package com.freshtawi.tawi.presentation.screen.browse.editCart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.freshtawi.tawi.presentation.screen.browse.addCart.component.AddCartTopBar
import com.freshtawi.tawi.presentation.screen.browse.addCart.component.AddEditCart
import com.freshtawi.tawi.presentation.screen.browse.browse.BrowseViewModel
import com.freshtawi.tawi.presentation.screen.browse.modalSheetLayout.BrowseBottomSheetType
import com.freshtawi.tawi.presentation.screen.browse.productDetail.component.CustomTab


@Composable
fun EditProductCartScreen(
    onClose: () -> Unit,
    isSheetOpen: Boolean,
) {
    EditProductCartScreenContent(
        onClose = onClose,
        isSheetOpen = isSheetOpen
    )
}

@Composable
fun EditProductCartScreenContent(
    browseViewModel: BrowseViewModel = viewModel(),
    onClose: () -> Unit,
    isSheetOpen: Boolean,
) {
    val (selected, setSelected) = remember { mutableStateOf(0) }
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    var currentBottomSheet: BrowseBottomSheetType? by remember { mutableStateOf(null) }
    val coroutineScope = rememberCoroutineScope()
    val addProductState by browseViewModel.browseState.collectAsStateWithLifecycle()


    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AddCartTopBar(
                title = "Edit Cart",
                onClose = { },
            )
            Spacer(modifier = Modifier.height(16.dp))
            addProductState.selectedProduct?.let {
                AddEditCart(
                    product = it,
                    onClickEdit = {
                        onClose()
                    }
                )
            }
            CustomTab(
                items = listOf("Tonnes", "Counts"),
                selectedItemIndex = selected,
                onClick = setSelected,
            )
           /* AddToCartButton(
                buttonText = "Confirm",
                onClick = {}
            )*/

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
                    isSheetOpen = isSheetOpen,
                )
            }
        }
    }*/

}

@Composable
fun EditCartItem(text: String) {
    Column {
        Text(text = text)
    }
}