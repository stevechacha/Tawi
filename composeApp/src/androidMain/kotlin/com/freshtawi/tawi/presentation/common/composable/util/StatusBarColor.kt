package com.freshtawi.tawi.presentation.common.composable.util

import androidx.compose.runtime.Composable
import android.app.Activity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat


@Composable
fun setInsetsController(isDark: Boolean) {
    val getPlatformContext = LocalContext.current
    val window = (getPlatformContext as Activity).window

    WindowCompat.getInsetsController(window, window.decorView)
        .isAppearanceLightStatusBars = !isDark

    WindowCompat.getInsetsController(window, window.decorView)
        .isAppearanceLightNavigationBars = !isDark
}

@Composable
fun getNavigationBarPadding(): PaddingValues {
    return WindowInsets.navigationBars.asPaddingValues()
}


@Composable
fun getStatusBarPadding(): PaddingValues {
    return WindowInsets.statusBars.asPaddingValues()
}