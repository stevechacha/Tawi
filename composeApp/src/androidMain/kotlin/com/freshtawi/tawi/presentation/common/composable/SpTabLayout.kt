package com.freshtawi.tawi.presentation.common.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun <E : Enum<E>>  SpAnimatedTabLayout(
    tabItems: List<E>,
    modifier: Modifier = Modifier,
    selectedTabColor: Color = MaterialTheme.colorScheme.primary,
    containerColor: Color = MaterialTheme.colorScheme.background,
    content: @Composable (E) -> Unit
) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    Column(modifier = modifier.fillMaxSize()) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 8.dp,
            containerColor = containerColor,
            contentColor = Color.Gray,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    color = selectedTabColor,
                    modifier = Modifier.fillMaxWidth()
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            tabItems.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    content = {
                            Text(
                                text = tab.name,
                                modifier = Modifier.padding(12.dp),
                                color = if (selectedTabIndex == index) selectedTabColor else Color(0xFFCACACA)
                            )
                        }
                )
            }
        }
        Spacer(Modifier.height(16.dp))
        content(tabItems[selectedTabIndex])
    }
}
