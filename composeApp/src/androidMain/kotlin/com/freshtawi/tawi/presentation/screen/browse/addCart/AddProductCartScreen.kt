package com.freshtawi.tawi.presentation.screen.browse.addCart

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freshtawi.tawi.presentation.screen.browse.addCart.component.AddCartTopBar
import com.freshtawi.tawi.presentation.screen.browse.addCart.component.AddEditCart
import com.freshtawi.tawi.presentation.screen.browse.modalSheetLayout.BrowseBottomSheetType
import com.freshtawi.tawi.presentation.screen.browse.modalSheetLayout.BrowseModalSheetLayout
import com.freshtawi.tawi.presentation.screen.browse.productDetail.component.CustomTab
import com.freshtawi.tawi.presentation.common.composable.VunaModalSheet
import com.freshtawi.tawi.domain.model.Product


@Composable
fun AddProductCartScreen(
    product: Product,
    id: Int = 0,
    navigateToCart:(Int)->Unit,
    onClose: () -> Unit,
    chosenDate: String,
    onDateChanged: (String) -> Unit
) {
    AddProductCartScreenContent(
        onClose = onClose,
        navigateToCart = {navigateToCart.invoke(id)},
        product = product,
        chosenDate = chosenDate,
        onDateChanged = onDateChanged,
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddProductCartScreenContent(
    navigateToCart: (Int) -> Unit,
    onClose: () -> Unit,
    product: Product,
    onDateChanged: (String) -> Unit,
    chosenDate: String

) {
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    val currentBottomSheet: BrowseBottomSheetType? by remember { mutableStateOf(null) }
    val (selected, setSelected) = remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AddCartTopBar(
            title = "Add to Cart",
            onClose = onClose,
        )

        AddEditCart(
            product = product,
            onClickEdit = {}
        )

        CustomTab(
            items = listOf("Tonnes", "Counts"),
            selectedItemIndex = selected,
            onClick = setSelected,
        )
        UpdateDateToCart(
            buttonText = "Confirm",
            hint = "Choose Pick up date",
            onClick = { navigateToCart.invoke(selected) },
            trailingIcon = {
                Box(
                    modifier = Modifier.size(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Event,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            },
            onDateChanged = onDateChanged,
            chosenDate = chosenDate
        )
    }
    if (isSheetOpen) {
        VunaModalSheet(
            onDismissRequest = { isSheetOpen = false },
        ) {
            currentBottomSheet?.let {
                BrowseModalSheetLayout(
                    bottomSheetType = it,
                    onClose = {},
                    isSheetOpen = isSheetOpen,
                    chosenDate = chosenDate,
                    onDateChanged = onDateChanged,
                    product = product,
                    navigateToCart = navigateToCart
                )
            }
        }
    }


}


@Composable
private fun UpdateDateToCart(
    modifier: Modifier = Modifier,
    hint: String = "",
    onClick:()->Unit,
    buttonText: String,
    chosenDate: String,
    onDateChanged:(String)->Unit,
    trailingIcon: @Composable (() -> Unit)? = null

) {
    Box(
        modifier = modifier.fillMaxWidth().height(60.dp)
            .border(
                2.dp, Color(0xffD9D9D9),
                shape = RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = chosenDate,
                onValueChange = onDateChanged,
                modifier = Modifier
                    .fillMaxWidth(0.55f)
                    .height(60.dp),
                placeholder = {
                    Text(
                        text = hint,
                        style = MaterialTheme.typography.labelMedium,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                trailingIcon = trailingIcon.takeIf { it != null },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            Button(
                onClick = onClick,
                modifier = Modifier.weight(1f).height(60.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xffD9D9D9),
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(
                    text = buttonText.uppercase(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700,
                    lineHeight = 24.sp,
                    letterSpacing = 0.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xff888888),
                )
            }
        }

    }

}

