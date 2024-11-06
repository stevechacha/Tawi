package com.freshtawi.tawi.presentation.screen.auth.pickLanguage

sealed class PickLanguageUIEffect {
    data object OnGoToPreferredLanguage : PickLanguageUIEffect()
}