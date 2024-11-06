package com.freshtawi.tawi.presentation.screen.farm.addPesticide

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freshtawi.tawi.R
import com.freshtawi.tawi.presentation.common.composable.util.DevicePreviews
import com.freshtawi.tawi.presentation.common.composable.util.FontScalePreviews


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PesticideCardItem() {
    OutlinedCard(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.background,
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
                    .size(54.dp),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_image),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Product Name Placeholder",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W700,
                    lineHeight = 18.sp
                )
                Text(
                    text = "Product Name/Variety",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400,
                    lineHeight = 15.sp
                )

                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp, bottom = 5.dp),
                    color = Color.Black
                )

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Last Sprayed",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W700,
                        lineHeight = 18.sp
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 0.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "DD/MM/YYYY",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W400,
                                lineHeight = 18.sp
                            )
                            Icon(
                                imageVector = Icons.Default.Event,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(24.dp).padding(start = 5.dp)
                            )
                        }
                    }

                }


            }

        }

    }


}

@Composable
@FontScalePreviews
@DevicePreviews
fun PesticideCardItemPreview() {
    PesticideCardItem()
}