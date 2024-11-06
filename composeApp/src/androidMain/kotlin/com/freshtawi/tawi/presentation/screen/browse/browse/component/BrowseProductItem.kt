package com.freshtawi.tawi.presentation.screen.browse.browse.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.freshtawi.tawi.R
import com.freshtawi.tawi.domain.model.Product
import com.freshtawi.tawi.domain.model.ProductAvailable
import com.freshtawi.tawi.presentation.common.composable.VunaRating

val RatingStarts = 1..5

@Composable
fun BrowseProductItem(
    modifier: Modifier = Modifier,
    product: Product,
    onProductItem: (Product) -> Unit = {},
) {
    OutlinedCard(
        onClick = { onProductItem(product)},
        modifier = modifier
            .fillMaxWidth()
            .heightIn(120.dp, 135.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.outlinedCardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color.LightGray)
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
                    .wrapContentHeight()
                    .background(Color.LightGray)
                    .width(120.dp),
                contentAlignment = Alignment.Center,
            ) {
                AsyncImage(
                    model = product.image,
                    contentDescription = product.productName,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${product.productName}(${product.variety})",
                        fontSize = 10.sp,
                        lineHeight = 15.sp,
                        fontWeight = FontWeight.W700
                    )
                    Text(
                        text = product.isReadyToSell,
                        fontSize = 10.sp,
                        lineHeight = 15.sp,
                        fontWeight = FontWeight.W700,
                        color = when (product.isReadyToSell) {
                            ProductAvailable.Ready -> { Color(0xFF33CA5D) }
                            ProductAvailable.NotReady -> { Color(0xffEF2F2F) }
                            else -> { Color(0xffEFDC2F) }
                        }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${product.productPrice}/Tonne",
                        fontSize = 10.sp,
                        lineHeight = 15.sp,
                    )
                    Text(
                        text = product.estimatedDate,
                        fontSize = 10.sp,
                        lineHeight = 15.sp,
                        )
                }
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp, bottom = 5.dp),
                    color = Color.Black
                )
                RatingItem(product = product)
                Spacer(modifier = Modifier.height(8.dp))
                FarmerLocationItem(product = product)

            }

        }

    }


}


@Composable
fun FarmerLocationItem(
    product: Product
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = product.farmNameHolder,
            fontSize = 10.sp,
            lineHeight = 15.sp,
            fontWeight = FontWeight.W700
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 5.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = product.farmerProductAddress,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 10.sp,
                    lineHeight = 15.sp,
                )
                Text(
                    text = "${product.farmerProvince}, ${product.farmerCounty} (24 km )",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 10.sp,
                    lineHeight = 15.sp,
                )
            }
        }

    }

}



@Composable
fun RatingItem(
    product: Product,
) {
    val review = product.reviews
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box {
                VunaRating(
                    rating = product.averageRating,
                    selectedIcon = painterResource(R.drawable.ic_filled_star_dark),
                    halfSelectedIcon = painterResource(id = R.drawable.ic_half_filled_star_dark),
                    iconsSize = 16.dp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = if (review>1) "$review Reviews" else "$review Review",
                fontSize = 10.sp,
                lineHeight = 15.sp
            )
        }
    }
}
