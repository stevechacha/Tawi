package com.freshtawi.tawi.presentation.screen.browse.productCart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.freshtawi.tawi.presentation.screen.browse.modalSheetLayout.BrowseBottomSheetType
import com.freshtawi.tawi.presentation.screen.browse.productList
import com.freshtawi.tawi.domain.model.Product
import com.freshtawi.tawi.domain.model.ProductAvailable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCartItem(
    modifier: Modifier = Modifier,
    product: Product,
    onProductItemClick: (Int) -> Unit,
) {
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    val currentBottomSheet: BrowseBottomSheetType? by remember { mutableStateOf(null) }
    val coroutineScope = rememberCoroutineScope()
    val productQuantity = product.productQuantity
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onProductItemClick(product.id) }
            .heightIn(115.dp, 118.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .heightIn(min = 80.dp, max = 110.dp)
                    .background(Color.LightGray)
                    .width(90.dp),
                contentAlignment = Alignment.Center,
            ) {
                AsyncImage(
                    model = product.image,
                    contentDescription = product.productName,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = "Mangoes (${product.variety})",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.W700,
                    lineHeight = 18.sp
                )
                Text(
                    text = "Ksh ${product.productPrice}/tonne",
                    fontSize = 8.sp,
                    lineHeight = 12.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                CartText(product = product)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Ksh.${product.totalSub}",
                        fontSize = 12.sp,
                        lineHeight = 18.sp
                    )

                    Box(
                        modifier = Modifier
                            .width(92.dp)
                            .height(24.dp)
                            .background(
                                color = Color.LightGray,
                                shape = RoundedCornerShape(32.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (productQuantity == 1) "$productQuantity tonne" else "$productQuantity tonnes",
                            textAlign = TextAlign.Center,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.W700,
                            lineHeight = 15.sp,
                        )

                    }

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
                    isSheetOpen = isSheetOpen
                )
            }
        }
    }*/

}

@Composable
private fun CartText(
    modifier: Modifier = Modifier,
    product: Product
) {
    Text(
        text = buildAnnotatedString {
            appendInlineContent("inlineContent", "Location")

            withStyle(
                style = SpanStyle(
                    fontSize = 14.sp
                )
            ){
                append(product.farmNameHolder)
            }

            append("\n")
            withStyle(
                style = SpanStyle(
                    color = when (product.isReadyToSell) {
                        ProductAvailable.Ready -> Color(0xFF33CA5D)
                        ProductAvailable.NotReady -> Color(0xffEF2F2F)
                        else -> Color(0xffEFDC2F)
                    },
                    fontSize = 10.sp
                )
            ) {
                append(product.isReadyToSell)
            }

            withStyle(
                style = SpanStyle(
                    fontSize = 10.sp
                )
            ){
                append(" | est.${product.estimatedDate}\n")

            }
            append("Pickup by ${product.productPickupDate}")
        },
        inlineContent = mapOf(
            Pair(
                "inlineContent",
                InlineTextContent(
                    Placeholder(
                        width = 16.sp,
                        height = 16.sp,
                        placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                }
            )
        ),
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.W700
    )

}

@Preview
@Composable
fun ProductCartItemPreview() {
    val product = productList.first()
    ProductCartItem(
        product = product,
        onProductItemClick = {}
    )
}