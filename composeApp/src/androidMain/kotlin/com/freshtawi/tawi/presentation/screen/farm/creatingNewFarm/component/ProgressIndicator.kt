package com.freshtawi.tawi.presentation.screen.farm.creatingNewFarm.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun NewFarmScreens() {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column {
        ProgressIndicator(currentPage = pagerState.currentPage)

        HorizontalPager(
            count = 4,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> Text("Farm Details Screen")
                1 -> Text("Product Details Screen")
                2 -> Text("Review Screen")
                3 -> Text("Review Screen")
            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp)
        )
    }
}

@Composable
fun ProgressIndicator(currentPage: Int) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ProgressStep(stepName = "Farm Details", isActive = currentPage == 0)
        ProgressStep(stepName = "Farm Details", isActive = currentPage == 0)
        ProgressStep(stepName = "Product Details", isActive = currentPage == 1)
        ProgressStep(stepName = "Review", isActive = currentPage == 2)
    }
}

@Composable
fun ProgressStep(stepName: String, isActive: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .height(4.dp).width(40.dp)
                .background(if (isActive) Color.Blue else Color.Gray, shape = MaterialTheme.shapes.small)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = stepName, style = MaterialTheme.typography.labelMedium, color = if (isActive) Color.Blue else Color.Gray)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
        NewFarmScreens()

}

