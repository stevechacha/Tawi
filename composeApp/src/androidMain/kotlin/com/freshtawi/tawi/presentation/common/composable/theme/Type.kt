package com.freshtawi.tawi.presentation.common.composable.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun displayLarge(): TextStyle {
    return TextStyle(
        fontSize = 34.sp,
        fontWeight = FontWeight.W400,
    )
}

@Composable
fun displayMedium(): TextStyle {
    return TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.W400,
    )
}

@Composable
fun displaySmall(): TextStyle {
    return TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W400,
    )
}

@Composable
fun headlineLarge(): TextStyle {
    return TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.W400,
    )
}

@Composable
fun headlineMedium(): TextStyle {
    return TextStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight.W400,
    )
}

@Composable
fun headlineSmall(): TextStyle {
    return TextStyle(
        fontSize = 8.sp,
        fontWeight = FontWeight.W400,
    )
}

@Composable
fun titleLarge(): TextStyle {
    return TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.W400,
    )
}

@Composable
fun titleMedium(): TextStyle {
    return TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W500,
        lineHeight = 24.sp
    )
}

@Composable
fun titleSmall ():TextStyle{
    return TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.W400,
    )
}

@Composable
fun bodyLarge(): TextStyle {
    return TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.W400,
    )
}

@Composable
fun bodyMedium(): TextStyle {
    return TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W400,
    )
}

@Composable
fun bodySmall(): TextStyle {
    return TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.W400,
    )
}

@Composable
fun labelLarge(): TextStyle {
    return TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.W400,
    )
}

@Composable
fun labelMedium(): TextStyle {
    return TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W400,
    )
}

@Composable
fun labelSmall(): TextStyle {
    return TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.W400,
    )
}
/*

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = displayFontFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily),
)*/

