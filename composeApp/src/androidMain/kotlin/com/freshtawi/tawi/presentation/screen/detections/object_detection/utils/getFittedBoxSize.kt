package com.freshtawi.tawi.presentation.screen.detections.object_detection.utils

import androidx.compose.ui.geometry.Size

fun getFittedBoxSize(containerSize: Size, boxSize: Size): Size {

    val boxAspectRatio = boxSize.width / boxSize.height
    val containerAspectRatio = containerSize.width / containerSize.height

    return if (boxAspectRatio > containerAspectRatio) {
        Size(
            width = containerSize.width,
            height =  (containerSize.width / boxSize.width) * boxSize.height,
        )
    } else {
        Size(
            height = containerSize.height,
            width =  (containerSize.height / boxSize.height) * boxSize.width,
        )
    }
}