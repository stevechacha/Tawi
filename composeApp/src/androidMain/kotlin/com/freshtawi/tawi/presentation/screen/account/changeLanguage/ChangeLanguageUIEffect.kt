package com.freshtawi.tawi.presentation.screen.account.changeLanguage

sealed class ChangeLanguageUIEffect {
    data object OnGoToPreferredLanguage : ChangeLanguageUIEffect()
}