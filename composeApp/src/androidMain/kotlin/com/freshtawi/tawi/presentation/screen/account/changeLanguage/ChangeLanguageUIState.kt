package com.freshtawi.tawi.presentation.screen.account.changeLanguage

import com.freshtawi.tawi.presentation.common.resources.util.LanguageCode


data class ChangeLanguageUIState(
    val isLoading: Boolean = false,
    val languages: List<LanguageUIState> = getLanguage(),
    val selectedLanguage: LanguageUIState = LanguageUIState(),
)

data class LanguageUIState(
    val name: String = "",
    val code: String = "",
    val image: String = ""
)


private fun getLanguage(): List<LanguageUIState> {
    return LanguageCode.entries.map {
        LanguageUIState(
            name = it.str,
            code = it.value,
            image = "${it.value}.jpg"
        )
    }
}
