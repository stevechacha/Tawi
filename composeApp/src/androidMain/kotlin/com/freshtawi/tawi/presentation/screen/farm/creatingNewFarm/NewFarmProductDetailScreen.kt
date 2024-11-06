package com.freshtawi.tawi.presentation.screen.farm.creatingNewFarm

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.freshtawi.tawi.presentation.common.composable.AppButton
import com.freshtawi.tawi.R
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.TextHintColor
import com.freshtawi.tawi.presentation.common.resources.Resources
import com.freshtawi.tawi.presentation.screen.farm.FarmNavigationScreen
import com.freshtawi.tawi.presentation.screen.farm.components.NewFarmHeaderFirst
import com.freshtawi.tawi.presentation.screen.farm.components.NewFarmTopBar


@Composable
fun NewFarmProductDetailScreen(navController: NavController) {
    NewFarmProductDetailScreenContent(
        navController = navController
    )

}


@Composable
fun NewFarmProductDetailScreenContent(
    navController: NavController
) {
    val context = LocalContext.current

    NewFarmHeaderFirst(
        topAppBar = {
            NewFarmTopBar(
                title = Resources.strings.newFarmTitle,
                onNavigateBack = { navController.popBackStack() },
                showBarContent = { HorizontalIndicator(currentPage = 2, pageCount = 3) },
                showActionBar = true,
                showSearchBar = true,
                showBackArrow = true
            )
        },
        bottomBar = {
            AppButton(
                text = stringResource(id = R.string.hint_continue),
                onClick = {
                    navController.navigate(FarmNavigationScreen.NewFarmReview.route)
                }
            )
        }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            item(span = { GridItemSpan(4) }) {
            }

            item(span = { GridItemSpan(4) }) {
                AddProductList(
                    title = "Product List",
                    numberItems = 4,
                    onClick = {
                        Toast.makeText(context, "Add Product", Toast.LENGTH_SHORT).show()
                    }
                )
            }
            items(6) {
                Box(
                    modifier = Modifier
                        .size(65.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.LightGray)
                )
            }
            item(span = { GridItemSpan(4) }) {
                AddProductList(
                    title = "Pesticides Used",
                    numberItems = 4,
                    onClick = {
                        Toast.makeText(context, "Pesticides Used", Toast.LENGTH_SHORT).show()
                    }
                )
            }

            items(10) {
                Box(
                    modifier = Modifier
                        .size(65.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.LightGray)
                )
            }

            item(span = { GridItemSpan(4) }) {
                AddProductList(
                    title = "Watering System Used",
                    numberItems = 4,
                    onClick = {
                        Toast.makeText(context, "Watering System Used", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
            }
            items(10) {
                Box(
                    modifier = Modifier
                        .size(65.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.LightGray)
                )
            }

            item(span = { GridItemSpan(4) }) {
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }

}

@Composable
fun AddProductList(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    title: String,
    numberItems: Int
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$title ($numberItems Items)",
                )
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable(interactionSource, null) {
                            onClick()
                        }
                        .align(Alignment.CenterVertically)
                )
            }

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(
                    color = Color.Black,
                    shape = RoundedCornerShape(6.dp)
                )
        )
    }

}


//Page2
@Composable
private fun HorizontalIndicator(currentPage: Int, pageCount: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
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
                        text = "Farm Details",
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
                        1 -> "Product Details"
                        else -> "Review"
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


