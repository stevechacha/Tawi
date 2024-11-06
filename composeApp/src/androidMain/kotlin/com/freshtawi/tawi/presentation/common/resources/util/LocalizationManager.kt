package com.freshtawi.tawi.presentation.common.resources.util

import androidx.compose.ui.unit.LayoutDirection
import com.freshtawi.tawi.presentation.common.resources.strings.en.English
import com.tecvuna.core.designsystem.resources.strings.IStringResources
import com.freshtawi.tawi.presentation.common.resources.strings.ar.Arabic
import com.freshtawi.tawi.presentation.common.resources.strings.sw.Swahili


object LocalizationManager {
    fun getStringResources(languageCode: LanguageCode): IStringResources {
        return when (languageCode) {
            LanguageCode.EN -> English()
            LanguageCode.SW -> Swahili()
            LanguageCode.AR -> Arabic()
        }
    }

    fun getLayoutDirection(languageCode: LanguageCode): LayoutDirection {
        return when (languageCode) {
            LanguageCode.EN -> LayoutDirection.Ltr
            LanguageCode.SW -> LayoutDirection.Ltr
            else -> LayoutDirection.Rtl
        }
    }
}
