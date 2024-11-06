package com.freshtawi.tawi.presentation.screen.browse.modalSheetLayout

import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import com.freshtawi.tawi.presentation.screen.browse.addCart.AddProductCartScreen
import com.freshtawi.tawi.presentation.screen.browse.editCart.EditProductCartScreen
import com.freshtawi.tawi.domain.model.Product


@Composable
fun BrowseModalSheetLayout(
    bottomSheetType: BrowseBottomSheetType,
    onClose: () -> Unit = {},
    isSheetOpen: Boolean = false,
    product: Product,
    chosenDate: String,
    onDateChanged:(String)->Unit,
    navigateToCart:(Int)->Unit,


    ) {

    AnimatedContent(
        label = "",
        targetState = bottomSheetType,
    ) { bottomSheetTypes ->
        when (bottomSheetTypes) {
            BrowseBottomSheetType.ADD_TO_CART -> {
                AddProductCartScreen(
                    onClose = { onClose() },
                    product = product,
                    chosenDate = chosenDate,
                    onDateChanged = onDateChanged,
                    navigateToCart = navigateToCart
                )
            }
            BrowseBottomSheetType.EDIT_ITEM_CART -> EditProductCartScreen(
                isSheetOpen = isSheetOpen,
                onClose = { onClose()}
            )
        }
    }


}

enum class BrowseBottomSheetType {
    ADD_TO_CART,
    EDIT_ITEM_CART,

}