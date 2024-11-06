package com.freshtawi.tawi.presentation.common.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.BottomBarColor

@Composable
fun HorizontalIndicator(pageCount: Int = 3, currentPage: Int) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 8.dp)
    ) {
        repeat(pageCount) { pageIndex ->
            val indicatorColor = if (pageIndex < currentPage) {
                MaterialTheme.colorScheme.onBackground
            } else {
                BottomBarColor
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(4.dp)
                    .padding(horizontal = 4.dp)
                    .background(
                        color = indicatorColor,
                        shape = RoundedCornerShape(6.dp)
                    )
            )
        }
    }
}

@Preview
@Composable
fun PreviewHorizontalIndicator() {
    Column {
        // Default state (none are highlighted)
        HorizontalIndicator(pageCount = 3, currentPage = 0)

        // When on the first page (first indicator highlighted)
        HorizontalIndicator(pageCount = 3, currentPage = 1)

        // When on the second page (first and second indicators highlighted)
        HorizontalIndicator(pageCount = 3, currentPage = 2)

        // When on the third page (all indicators highlighted)
        HorizontalIndicator(pageCount = 3, currentPage = 3)
    }
}

