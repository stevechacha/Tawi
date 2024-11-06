package com.freshtawi.tawi.presentation.screen.browse.productDetail.component


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Hd
import androidx.compose.material.icons.filled.Propane
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.ColorBorder
import com.freshtawi.tawi.domain.model.Product


@Composable
fun ProductDetailListContent(
    modifier: Modifier = Modifier,
    product: Product
) {
    val (selected, setSelected) = remember { mutableStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.70f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Product Name
                Text(
                    text = "Super Sweet ${product.productName} (${product.variety}) Very Good Tas...",
                    textAlign = TextAlign.Start,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 21.sp
                )

                // Product Price
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .clip(CircleShape)
                            .background(ColorBorder),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Propane,
                            contentDescription = null,
                            modifier = Modifier.size(12.dp)
                        )

                    }
                    Text(
                        text = product.productPrice.toString(),
                        textAlign = TextAlign.Start,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W700,
                        modifier = Modifier.padding(start = 4.dp),
                        lineHeight = 18.sp
                    )
                    Text(
                        text = "/ Tonne",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 4.dp),
                        lineHeight = 18.sp

                    )
                }

                // Product Quantity

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.size(16.dp).clip(CircleShape).background(ColorBorder),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Propane,
                            contentDescription = null,
                            modifier = Modifier.size(12.dp)
                        )
                    }
                    Text(
                        text = "${product.productQuantity}+",
                        textAlign = TextAlign.Start,
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                    Text(
                        text = "counts(",
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 4.dp),
                        fontSize = 12.sp,
                        lineHeight = 18.sp
                    )
                    Text(
                        text = "10+",
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                    Text(
                        text = "tonnes)",
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 4.dp),
                        fontSize = 12.sp,
                        lineHeight = 18.sp
                    )
                }

            }

            Spacer(modifier = Modifier.width(16.dp))

            // Product Status

            Box(
                modifier = Modifier
                    .width(130.dp)
                    .height(105.dp)
                    .border(
                        2.dp, MaterialTheme.colorScheme.onBackground,
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Ready",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "est.",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp,
                            lineHeight = 15.sp,
                        )
                        Text(
                            text = product.estimatedDate,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = "Verified",
                            modifier = Modifier
                                .size(12.dp)
                                .align(Alignment.CenterVertically)
                        )
                        Text(
                            text = "Verified",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp,
                            lineHeight = 15.sp,
                        )
                    }

                }
            }

        }

        // Product Description Header
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Hd, contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = "Description",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 4.dp),
                fontSize = 12.sp,
                lineHeight = 18.sp,
            )
        }
        TruncatedTextWithMore(
            text = product.productDescription
        )
    }
}