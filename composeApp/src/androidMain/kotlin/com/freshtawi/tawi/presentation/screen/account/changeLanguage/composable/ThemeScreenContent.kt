package com.freshtawi.tawi.presentation.screen.account.changeLanguage.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.freshtawi.tawi.presentation.common.resources.util.LanguageCode

@Composable
fun ThemeScreenContent(
    onSelectTheme: (String) -> Unit,
    selectedTheme: String,
) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        ThemeItem(
            languageName = LanguageCode.EN.value,
            onSelectTheme = onSelectTheme,
            isSelected = selectedTheme == LanguageCode.EN.str,
        )
        ThemeItem(
            languageName = LanguageCode.AR.value,
            onSelectTheme = onSelectTheme,
            isSelected = selectedTheme == LanguageCode.AR.str

        )
        ThemeItem(
            languageName = LanguageCode.SW.value,
            onSelectTheme = onSelectTheme,
            isSelected = selectedTheme == LanguageCode.SW.str
        )


    }

}