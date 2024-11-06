package com.freshtawi.tawi.presentation.screen.account.changeLanguage


import com.freshtawi.tawi.presentation.common.base.BaseScreenModel
import com.freshtawi.tawi.domain.utils.ErrorState
import com.freshtawi.tawi.presentation.common.resources.util.LanguageCode
import com.freshtawi.tawi.domain.usecases.IManageSettingUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class ChangeLanguageViewModel(
    private val userPreferences: IManageSettingUseCase,
) : BaseScreenModel<ChangeLanguageUIState, ChangeLanguageUIEffect>(ChangeLanguageUIState()),
    ChangeLanguageInteractionListener {

    private val _language: MutableStateFlow<LanguageCode> = MutableStateFlow(LanguageCode.EN)
    val language: StateFlow<LanguageCode> = _language.asStateFlow()

   init {
       getUserLanguageCode()
   }

    private fun getUserLanguageCode() {
        viewModelScope.launch(Dispatchers.IO) {
            userPreferences.getUserLanguageCode().distinctUntilChanged().collectLatest { lang ->
                _language.update {
                    LanguageCode.entries.find { languageCode -> languageCode.value == lang }
                        ?: LanguageCode.EN
                }
            }
        }
    }



    override fun onLanguageSelected(language: LanguageUIState) {
        tryToExecute(
            { userPreferences.saveLanguageCode(language.code) },
            ::onSavedLanguageSuccess,
            ::onError
        )
    }

    private fun onSavedLanguageSuccess(unit: Unit) {
        sendNewEffect(ChangeLanguageUIEffect.OnGoToPreferredLanguage)
    }

    private fun onError(errorState: ErrorState) {
        println(errorState)
    }
}
