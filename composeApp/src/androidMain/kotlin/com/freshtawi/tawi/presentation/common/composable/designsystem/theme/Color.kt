package com.freshtawi.tawi.presentation.common.composable.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color


val PrimaryColor = Color(0xff9BBB59)
val PrimaryLightColor = Color(0xffffe3d5)

val SecondaryColor = Color(0xff6367FF)
val SecondaryLightColor = Color(0xffBEC1FF)

val PrimaryTextColor = Color(0xffffffff)
val SecondaryTextColor = Color(0xff000000)

val SurfaceDark = Color(0xFF3A3A3A)
val SurfaceLight = Color(0xFFFFFFFF)

val BackgroundLightColor = Color(0xffF1F0F5)
val BackgroundDarkColor = Color(0xff121212)

val BottomBarColor = Color(0xffD9D9D9)
val HintGray = Color(0xffD9D9D9)
val TextHintColor = Color(0xff888888)

val ErrorColor = Color(0xFFFF8989)
val OnErrorColor = Color(0xFF000000)

val ColorBorder = Color(0xFFD9D9D9)
val PrimaryColorHint = Color(0xFFD9D9D9)

val DarkBlue = Color(0xFF1B3B5A)
val DeepBlue = Color(0xFF102840)

val BoundingBox = Color(0xFFFF6F00)
val TurquoiseGrey = Color(0xFF355E5A)
val LightBlue = Color(0xFF00BCD4)

val hover = Color(0xFFFFFAFA)
val successContainer = Color(0xFFF0FFF7)
val warning = Color(0xFFF2BD00)






val colorBackground
    @Composable
    @ReadOnlyComposable
    get() = MaterialTheme.colorScheme.surface

val colorOnBackground
    @Composable
    @ReadOnlyComposable
    get() = MaterialTheme.colorScheme.onSurface

val colorButton
    @Composable
    @ReadOnlyComposable
    get() =
        MaterialTheme.colorScheme.secondaryContainer

val colorOnButton
    @Composable
    @ReadOnlyComposable
    get() =
        MaterialTheme.colorScheme.onSurfaceVariant

val colorEditor
    @Composable
    @ReadOnlyComposable
    get() =
        MaterialTheme.colorScheme.surface

val colorOnEditor
    @Composable
    @ReadOnlyComposable
    get() = MaterialTheme.colorScheme.onSurface

