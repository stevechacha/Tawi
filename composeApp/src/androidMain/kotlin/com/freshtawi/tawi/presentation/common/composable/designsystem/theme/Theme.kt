package com.freshtawi.tawi.presentation.common.composable.designsystem.theme

import android.content.Context
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.util.Locale

val Context.dataStore by preferencesDataStore("settings")
var Context.appTheme by mutableStateOf(ThemeMode.SYSTEM)
var Context.appLocale: Locale? by mutableStateOf(null)
var Context.systemLocale: Locale? by mutableStateOf(null)

enum class ThemeMode { LIGHT, NIGHT, SYSTEM }

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColor,
    onPrimary = PrimaryTextColor,
    secondary = SecondaryColor,
    onSecondary = SecondaryTextColor,
    tertiary = PrimaryLightColor,
    onTertiary = PrimaryTextColor,
    background = BackgroundLightColor,
    onBackground = Color.Black,
    surface = SurfaceLight,
    onSurface = Color.Black,
    surfaceVariant = SurfaceLight,
    onSurfaceVariant = Color.Black,
    secondaryContainer = PrimaryColor,
    onSecondaryContainer = Color.White,
    error = ErrorColor,
    onError = OnErrorColor
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    onPrimary = PrimaryTextColor,
    secondary = SecondaryColor,
    onSecondary = SecondaryTextColor,
    tertiary = PrimaryLightColor,
    onTertiary = PrimaryTextColor,
    background = BackgroundLightColor,
    onBackground = Color.Black,
    surface = SurfaceLight,
    onSurface = Color.Black,
    surfaceVariant = SurfaceLight,
    onSurfaceVariant = Color.Black,
    secondaryContainer = PrimaryColor,
    onSecondaryContainer = Color.White,
    error = ErrorColor,
    onError = OnErrorColor
)

@Composable
fun isNightMode(): Boolean = when (LocalContext.current.appTheme) {
    ThemeMode.LIGHT -> false
    ThemeMode.NIGHT -> true
    else -> isSystemInDarkTheme()
}

@Composable
fun TheTawiTheme(
    darkTheme: Boolean = isNightMode(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    val systemUiController = rememberSystemUiController()
    if (!view.isInEditMode) {
        SideEffect {
            systemUiController.setSystemBarsColor(
                color =  colorScheme.background
            )
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = shapes,
        content = content
    )
}

suspend fun switchTheme(context: Context, mode: ThemeMode) {
    context.dataStore.edit {
        it[stringPreferencesKey("theme")] = mode.toString()
    }

    context.appTheme = mode
}

fun syncTheme(context: Context) {
    val currentValue = runBlocking { context.dataStore.data.first() }

    val mode = ThemeMode.valueOf(
        currentValue[stringPreferencesKey("theme")] ?: ThemeMode.SYSTEM.toString()
    )

    context.appTheme = mode
}