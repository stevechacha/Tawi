package com.freshtawi.tawi.presentation.common.resources

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalLayoutDirection
import com.tecvuna.core.designsystem.resources.strings.IStringResources
import com.freshtawi.tawi.presentation.common.resources.util.LanguageCode
import com.freshtawi.tawi.presentation.common.resources.util.LocalizationManager
import com.freshtawi.tawi.presentation.common.composable.util.setInsetsController
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.TheTawiTheme

private val localDrawableResources = staticCompositionLocalOf { DrawableResources() }
private val localStringResources = staticCompositionLocalOf<IStringResources> {
    error("CompositionLocal IStringResources not present")
}

@Composable
fun TawiTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    languageCode: LanguageCode,
    content: @Composable () -> Unit,
) {
    val drawableResources = if (useDarkTheme) BpDrawableDarkResources else DrawableResources()

    CompositionLocalProvider(
        localDrawableResources provides drawableResources,
        localStringResources provides LocalizationManager.getStringResources(languageCode),
        LocalLayoutDirection provides LocalizationManager.getLayoutDirection(languageCode)
    ) {
        TheTawiTheme(darkTheme = useDarkTheme) {
            setInsetsController(useDarkTheme)
            content()
        }
    }
}

object Resources {
    val images: DrawableResources
        @Composable
        @ReadOnlyComposable
        get() = localDrawableResources.current

    val strings: IStringResources
        @Composable
        @ReadOnlyComposable
        get() = localStringResources.current
}
