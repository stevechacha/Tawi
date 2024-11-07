package com.freshtawi.tawi.presentation.screen.farm.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.freshtawi.tawi.domain.model.Farm
import com.freshtawi.tawi.domain.model.ProductCrop
import com.freshtawi.tawi.R
import com.freshtawi.tawi.presentation.common.resources.Resources


@Composable
fun NewFarmCardComponent(
    farm: Farm,
    onItemClick: (Farm) -> Unit = {},
) {
    val farmHa = Resources.strings.farmNameHint
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onItemClick(farm) }
            .border(1.dp, Color.LightGray,
                RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .wrapContentSize()
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center,
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(farm.farmImageUrl).build(),
                    contentDescription = Resources.strings.farm,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(108.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = farm.farmName,
                    fontSize = 10.sp,
                    lineHeight = 15.sp,
                    fontWeight = FontWeight.W700
                )

                Text(
                    text = farm.farmerName,
                    fontSize = 10.sp,
                    lineHeight = 15.sp,
                )
                val farmHa = farm.farmSizeAcres.toInt()
                Text(
                    text = if (farmHa > 1) "$farmHa Hectares" else "$farmHa Hectare",
                    fontSize = 10.sp,
                    lineHeight = 15.sp,
                )

                BorderDivider(
                    productCropItems = farm.product.size
                )
                Spacer(modifier = Modifier.height(5.dp))

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(farm.product) { product ->
                        CardRow(
                            productCrop = product
                        )
                    }
                }
            }

        }
    }


}

@Composable
fun CardRow(
    modifier: Modifier = Modifier,
    productCrop: ProductCrop
) {
    Box(
        modifier = modifier
            .size(39.dp)
            .background(Color.LightGray, RoundedCornerShape(8.dp))
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(productCrop.image).build(),
            contentDescription = Resources.strings.farm,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .clip(shape = RoundedCornerShape(8.dp))
                .size(39.dp)
        )
    }

}

@Composable
fun BorderDivider(
    productCropItems: Int
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = Resources.strings.productList,
                    fontSize = 10.sp,
                    lineHeight = 15.sp,
                    fontWeight = FontWeight.W700
                )
                Text(
                    text = if (productCropItems > 1) "$productCropItems items" else "$productCropItems item",
                    fontSize = 10.sp,
                    lineHeight = 15.sp,
                    textDecoration = TextDecoration.Underline
                )
            }

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(
                    color = Color.Black,
                    shape = RoundedCornerShape(8.dp)
                )
        )
    }

}



