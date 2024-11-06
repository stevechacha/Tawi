package com.freshtawi.tawi.presentation.common.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun ReusableTabLayout(
    pages: List<String>,
    pagerState: PagerState,
    currentIndex: Int,
    pageMapper: (Int) -> Int,
    paddingValue: PaddingValues,
    onPageSelected: (Int) -> Unit,
    content: @Composable (Int) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        ScrollableTabRow(
            selectedTabIndex = currentIndex,
            indicator = { tabPositions ->
                SecondaryIndicator(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[currentIndex])
                        .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.primary
                )
            },
            containerColor = MaterialTheme.colorScheme.background,
            edgePadding = 8.dp,
            divider = {},
            modifier = Modifier.padding(
                top = paddingValue.calculateTopPadding(),
            )
        ) {
            pages.forEachIndexed { index, title ->
                val selectedTab = currentIndex == index
                Tab(
                    text = {
                        Text(
                            title,
                            style = MaterialTheme.typography.titleMedium,
                            color = if (selectedTab) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
                        )
                    },
                    selected = currentIndex == index,
                    onClick = {
                        coroutineScope.launch {
                            onPageSelected(index)
                        }
                    }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            pageSpacing = 10.dp,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { index ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(all = 5.dp)
                    .padding(top = 12.dp)
            ) {
                content(pageMapper(index))
            }
        }
    }
}

