
package com.freshtawi.tawi.presentation.screen.detections.object_detection.composables

import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.freshtawi.tawi.R

@Composable
fun TabsTopBar(
    selectedTabIndex: Int,
    setSelectedTabIndex: (Int) -> Unit,
) {
    TabRow(selectedTabIndex = selectedTabIndex) {
        Tab(
            unselectedContentColor = Color.Gray,
            selected = selectedTabIndex == 0,
            onClick = { setSelectedTabIndex(0) },
            text = { Text("Camera") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_photo_camera_24),
                    contentDescription = "Camera icon"
                )
            },
        )
        Tab(
            unselectedContentColor = Color.Gray,
            selected = selectedTabIndex == 1,
            onClick = { setSelectedTabIndex(1) },
            text = { Text("Gallery") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_photo_library_24),
                    contentDescription = "Gallery icon"
                )
            },
        )
    }
}