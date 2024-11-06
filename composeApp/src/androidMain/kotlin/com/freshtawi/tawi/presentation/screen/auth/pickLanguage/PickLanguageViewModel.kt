package com.freshtawi.tawi.presentation.screen.auth.pickLanguage


import com.freshtawi.tawi.domain.usecases.IManageSettingUseCase
import com.freshtawi.tawi.presentation.common.base.BaseScreenModel
import com.freshtawi.tawi.domain.utils.ErrorState


class PickLanguageViewModel(
    private val userPreferences: IManageSettingUseCase,
) : BaseScreenModel<PickLanguageUIState, PickLanguageUIEffect>(PickLanguageUIState()),
    PickLanguageInteractionListener {



    override fun onLanguageSelected(language: LanguageUIState) {
        tryToExecute(
            { userPreferences.saveLanguageCode(language.code) },
            ::onSavedLanguageSuccess,
            ::onError
        )
    }

    private fun onSavedLanguageSuccess(unit: Unit) {
        sendNewEffect(PickLanguageUIEffect.OnGoToPreferredLanguage)
    }

    private fun onError(errorState: ErrorState) {
        println(errorState)
    }
}
