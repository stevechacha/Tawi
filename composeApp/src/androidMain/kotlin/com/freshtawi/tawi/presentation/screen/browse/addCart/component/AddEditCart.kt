package com.freshtawi.tawi.presentation.screen.browse.addCart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freshtawi.tawi.domain.model.Product

@Composable
fun AddEditCart(
    product: Product,
    onClickEdit: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = "Super Sweet ${product.productName} (${product.variety}) Very Good Taste and Very Filling Fruits",
            fontWeight = FontWeight.W700
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = product.farmNameHolder,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W700,
                    lineHeight = 18.sp,
                    letterSpacing = 0.sp,
                )
            }
            Text(
                text = "Reday| ${product.estimatedDate}",
                fontSize = 10.sp,
                fontWeight = FontWeight.W700,
                lineHeight = 15.sp,
                letterSpacing = 0.sp,
            )

        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .height(24.dp)
                    .width(92.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${product.productQuantity} Tonnes",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.W700,
                    lineHeight = 15.sp,
                    letterSpacing = 0.sp,
                )
            }

            Text(
                text = "EDIT",
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable { onClickEdit() },
                fontSize = 12.sp,
                fontWeight = FontWeight.W700,
                lineHeight = 18.sp,
                letterSpacing = 0.sp,
                textAlign = TextAlign.Left
            )
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    text = "Ksh${product.totalAmount}",
                    textAlign = TextAlign.End,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W700,
                    lineHeight = 18.sp,
                    letterSpacing = 0.sp,
                )
                Text(
                    text = "(KSh${product.productPrice}/tonne)",
                    textAlign = TextAlign.End,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.W400,
                    lineHeight = 18.sp,
                    letterSpacing = 0.sp
                )
            }
        }
    }

}


