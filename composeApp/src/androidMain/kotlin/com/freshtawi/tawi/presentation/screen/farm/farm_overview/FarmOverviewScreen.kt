package com.freshtawi.tawi.presentation.screen.farm.farm_overview

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.freshtawi.tawi.presentation.common.composable.AppToolbar
import com.freshtawi.tawi.presentation.common.composable.TwoButtonsVertical
import com.freshtawi.tawi.presentation.screen.farm.creatingNewFarm.AddProductList
import com.freshtawi.tawi.presentation.screen.farm.farm_overview.component.StatusCard

@Composable
fun FarmOverviewScreen(
    navigator: NavController
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            AppToolbar(
                onNavigateBack = { navigator.popBackStack() },
                title = "Farm Overview",
                showBackArrow = true,
                action = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icons.Default.Edit
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(paddingValues)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                item(span = { GridItemSpan(4) }) {

                    Text(text = "Farm Name")

                }

                item(span = { GridItemSpan(4) }) {
                    StatusCard()
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
                    AddProductList(
                        title = "Watering System Used",
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
                    RecordScreen()
                }
                item(span = { GridItemSpan(4) }) {
                    TwoButtonsVertical(
                        topButtonText = "SCHEDULE PICKUP",
                        bottomButtonText = "VIEW AS BUYER",
                        onTopButtonClick = {},
                        onBottomButtonClick = { /*TODO*/ },
                        enableTopButton = true,
                        enableBottomButton = true,
                        showTopButton = true
                    )
                }


            }

        }

    }

}

@Composable
@Preview
fun PreviewFarmOverView() {
    FarmOverviewScreen(
        navigator = NavController(LocalContext.current)
    )
    
}
