package com.freshtawi.tawi.presentation.screen.account.changeLanguage

import com.freshtawi.tawi.presentation.common.base.BaseInteractionListener


interface ChangeLanguageInteractionListener : BaseInteractionListener {
    fun onLanguageSelected(language: LanguageUIState)

}