package com.freshtawi.tawi.presentation.screen.farm.creatingNewFarm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.freshtawi.tawi.R
import com.freshtawi.tawi.presentation.common.composable.AppButton
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.TextHintColor
import com.freshtawi.tawi.presentation.common.resources.Resources
import com.freshtawi.tawi.presentation.screen.farm.FarmNavigationScreen
import com.freshtawi.tawi.presentation.screen.farm.components.NewFarmTopBar

@Composable
fun NewFarmReviewScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            NewFarmTopBar(
                onNavigateBack = {navController.popBackStack()},
                showBarContent = { HorizontalIndicator(currentPage = 3, pageCount = 3) },
                showActionBar = true,
                showSearchBar = true,
                showBackArrow = true
            )
        },
        bottomBar = {
            AppButton(
                text = stringResource(id = R.string.hint_done),
                onClick = {
                    navController.navigate(FarmNavigationScreen.AddProduct.route)
                }
            )

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {

            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Farm Details",
                        color = TextHintColor
                    )
                    Text(
                        text = "EDIT",
                        color = TextHintColor,
                        textDecoration = TextDecoration.Underline
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Product Details",
                        color = TextHintColor
                    )
                    Text(
                        text = "EDIT",
                        color = TextHintColor,
                        textDecoration = TextDecoration.Underline
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(start = 16.dp)
                ) {
                    Text(text = "Farm Name")
                    Text(text = "Farm Name")
                }


            }
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Product Details",
                        color = TextHintColor
                    )
                    Text(
                        text = "EDIT",
                        color = TextHintColor,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }


            ProductReviewDetail()


        }

    }

}


@Composable
@Preview
fun ProductReviewDetail() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "ProductList",
                fontSize = 10.sp,
                lineHeight = 15.sp,
                fontWeight = FontWeight.W700,
                textAlign = TextAlign.Left
            )
            Text(
                text = "No Product Added",
                fontSize = 10.sp,
                lineHeight = 15.sp,
                fontWeight = FontWeight.W700,
                textAlign = TextAlign.Right
            )
        }

        LazyColumn {
            items(10) {
                ReviewItem()
            }
        }


    }
}

@Composable
fun ReviewItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .padding(start = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(5.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            )
            Text(
                text = "Farm Name",
                modifier = Modifier.padding(start = 8.dp),
                color = TextHintColor,
                fontSize = 10.sp,
                lineHeight = 15.sp,
                fontWeight = FontWeight.W400
            )
        }

    }

}



@Composable
private fun HorizontalIndicator(currentPage: Int, pageCount: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount) { pageIndex ->
            val isCurrentPage = pageIndex == currentPage
            val indicatorColor = if (isCurrentPage) {
                Color.LightGray

            } else {
                MaterialTheme.colorScheme.onBackground

            }

            if (pageIndex > 0) {
                // Add one line between indicators for all except the first one
                Box(
                    modifier = Modifier
                        .width(95.dp)
                        .align(Alignment.CenterVertically)
                        .height(2.dp)
                        .padding(horizontal = 2.dp)
                        .background(indicatorColor)
                )
            }

            if (pageIndex == 0) {
                // Only show the text and title for the first index
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(if (isCurrentPage) Color.LightGray else indicatorColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "1",
                            color = if (isCurrentPage) Color.LightGray else Color.White,
                            fontWeight = FontWeight.W700,
                            fontSize = 8.sp,
                            lineHeight = 12.sp,
                            textAlign = TextAlign.Center
                        )
                    }


                    Text(
                        text = Resources.strings.farmDetails,
                        color = if (isCurrentPage) TextHintColor else MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.W400,
                        fontSize = 8.sp,
                        lineHeight = 12.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                // Show the text and title for other indices
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(if (isCurrentPage) Color.LightGray else indicatorColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = (pageIndex + 1).toString(),
                            color = if (isCurrentPage) Color.White else Color.White,
                            fontWeight = FontWeight.W700,
                            fontSize = 8.sp,
                            lineHeight = 12.sp,
                            textAlign = TextAlign.Center

                        )
                    }

                    val title = when (pageIndex) {
                        1 -> Resources.strings.productDetail
                        else -> Resources.strings.review
                    }

                    Text(
                        text = title,
                        color = if (isCurrentPage) TextHintColor else MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.W400,
                        fontSize = 8.sp,
                        lineHeight = 12.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}