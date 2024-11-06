package com.freshtawi.tawi.presentation.screen.detections.object_detection.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.BoundingBox


@Composable
fun MediaPipeBanner(
    onOptionsButtonClick: (() -> Unit)? = null,
    onBackButtonClick: (() -> Unit)? = null,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(MaterialTheme.colorScheme.background),
    ) {
        if (onBackButtonClick != null) {
            IconButton(
                onClick = onBackButtonClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Backward arrow icon",
                    tint = BoundingBox
                )
            }
        }

        if (onOptionsButtonClick != null) {
            IconButton(
                onClick = onOptionsButtonClick,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = "Settings icon",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}