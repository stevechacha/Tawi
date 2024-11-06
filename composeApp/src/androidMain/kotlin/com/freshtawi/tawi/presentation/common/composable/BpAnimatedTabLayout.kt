package com.freshtawi.tawi.presentation.common.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun <E : Enum<*>> BpAnimatedTabLayout(
    tabItems: List<E>,
    selectedTab: E,
    onTabSelected: (E) -> Unit,
    modifier: Modifier = Modifier,
    horizontalPadding: Dp = 4.dp,
    selectedTabColor: Color = MaterialTheme.colorScheme.primary,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    shape: Shape = RoundedCornerShape(8.dp),
    borderStroke: BorderStroke? = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
    content: @Composable (E) -> Unit
) {
    var selectedTabIndex by remember { mutableStateOf(tabItems.indexOf(selectedTab)) }
    var tabPositions by remember { mutableStateOf(listOf<TabPosition>()) }
    val coroutineScope = rememberCoroutineScope()

    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        edgePadding = 16.dp,
        containerColor = containerColor,
        contentColor = selectedTabColor,
        indicator = { tabPositions ->
            val currentTabPosition = tabPositions[selectedTabIndex]
            Box(
                Modifier
                    .tabIndicatorOffset(currentTabPosition)
                    .fillMaxWidth()
                    .height(3.dp)
                    .background(selectedTabColor)
            )
        },
        modifier = modifier
            .clip(shape)
            .then(
                Modifier.takeIf { borderStroke != null }?.border(borderStroke!!, shape) ?: Modifier
            )
    ) {
        tabItems.forEachIndexed { index, tab ->
            val isSelected = index == selectedTabIndex
            Tab(
                selected = isSelected,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(tab)
                    coroutineScope.launch {
                        selectedTabIndex = index
                    }
                },
                modifier = Modifier.padding(
                    start = if (index == 0) horizontalPadding else 0.dp,
                    end = if (index == tabItems.size - 1) horizontalPadding else 0.dp
                ),
                content = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        content(tab)
                    }
                }
            )
        }
    }
}
