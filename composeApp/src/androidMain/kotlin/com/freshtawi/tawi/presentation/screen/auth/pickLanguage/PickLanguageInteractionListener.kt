package com.freshtawi.tawi.presentation.screen.auth.pickLanguage

import com.freshtawi.tawi.presentation.common.base.BaseInteractionListener


interface PickLanguageInteractionListener : BaseInteractionListener {
    fun onLanguageSelected(language: LanguageUIState)

}