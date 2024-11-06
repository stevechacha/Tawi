package com.freshtawi.tawi.presentation.screen.browse.productDetail.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AddToCartButton(
    modifier: Modifier = Modifier,
    hint: String = "",
    onClick:()->Unit,
    buttonText: String,
    productQuantity: String,
    onProductQuantityChanged:(String)->Unit,
    trailingIcon: @Composable (() -> Unit)? = null

) {
    Box(
        modifier = modifier.fillMaxWidth().height(60.dp)
            .border(2.dp, Color(0xffD9D9D9),
            shape = RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = productQuantity,
                onValueChange = onProductQuantityChanged,
                modifier = Modifier.fillMaxWidth(0.55f).height(60.dp),
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
