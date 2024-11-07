package com.freshtawi.tawi.presentation.screen.farm.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freshtawi.tawi.R
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.Dimen

val RatingStarts = 1..5


@Composable
@Preview
fun ProductCardItem() {
    OutlinedCard(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = Color.White,
        ),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .wrapContentHeight()
                    .background(Color.LightGray)
                    .width(120.dp),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_start),
                    contentDescription = null,
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
                    Text(text = "Product Name(Variety)", fontSize = 14.sp)
                    Text(text = "Not Ready", fontSize = 14.sp)
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Price/Tonne", fontSize = 14.sp)
                    Text(text = "Date", fontSize = 14.sp)
                }

                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp, bottom = 5.dp),
                    color = Color.Black
                )

                RatingItem()

                FarmerLocationItem()

            }

        }

    }


}

@Composable
fun FarmerLocationItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Farm Name Placeholder ")
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Default.MyLocation,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Farmer Name",
                    fontSize = 14.sp
                )
                Text(
                    text = "Province , County (24 km )",
                    fontSize = 14.sp
                )
            }

        }


    }

}

@Composable
private fun Rating(
    selectedValue: Int,
    rangeValues: IntRange
) {
    Row {
        for (value in rangeValues) {
            Image(
                painter = painterResource(
                    if (value <= selectedValue) R.drawable.ic_start
                    else R.drawable.ic_unstart
                ),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                contentDescription = "Rating star",
                modifier = Modifier
                    .height(Dimen.Size.icon)
                    .padding(end = Dimen.Space.main)
            )
        }
    }
}

@Composable
@Preview
fun PreviewRating() {
    Rating(
        selectedValue = 3,
        rangeValues = RatingStarts
    )
}

@Composable
fun RatingItem() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box {
                Rating(
                    selectedValue = 3,
                    rangeValues = RatingStarts
                )

            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "24 Reviews",
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


data class ProductItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val image: String,
    val quantity: Int,
    val category: String,
    val subCategory: String,
    val location: String,
    val isReadyToSell: Boolean,
)