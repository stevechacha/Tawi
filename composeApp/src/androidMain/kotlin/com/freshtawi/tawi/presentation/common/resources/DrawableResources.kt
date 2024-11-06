package com.freshtawi.tawi.presentation.common.resources

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.graphics.vector.ImageVector
import com.freshtawi.tawi.R

data class DrawableResources(
    val vunaIcon: Int = R.drawable.ic_back,
    val vunaLogo: Int = R.drawable.ic_back,
    val filledStar: Int = R.drawable.ic_filled_star_light,
    val arrowBack: ImageVector = Icons.AutoMirrored.Filled.ArrowBack,
    val close: ImageVector = Icons.Default.Close
)

val BpDrawableDarkResources = DrawableResources(
    filledStar = R.drawable.ic_filled_star_dark
)
