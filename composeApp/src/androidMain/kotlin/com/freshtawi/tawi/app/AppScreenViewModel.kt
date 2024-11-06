package com.freshtawi.tawi.app

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freshtawi.tawi.domain.usecases.IManageSettingUseCase
import com.freshtawi.tawi.presentation.common.resources.util.LanguageCode
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppScreenModel (private val manageUser: IManageSettingUseCase) : ViewModel() {
    private val _language: MutableStateFlow<LanguageCode> = MutableStateFlow(LanguageCode.EN)
    val language: StateFlow<LanguageCode> = _language.asStateFlow()

    private val _isFirstTimeOpenApp: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isFirstTimeOpenApp: StateFlow<Boolean> = _isFirstTimeOpenApp.asStateFlow()



    val user = mutableStateOf(Firebase.auth.currentUser)

    init {
        getUserLanguageCode()
        getInitScreen()
    }

    private fun getUserLanguageCode() {
        viewModelScope.launch(Dispatchers.IO) {
            manageUser.getUserLanguageCode().distinctUntilChanged().collectLatest { lang ->
                _language.update {
                    LanguageCode.entries.find { languageCode -> languageCode.value == lang }
                        ?: LanguageCode.EN
                }
            }
        }
    }

    private fun getInitScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            _isFirstTimeOpenApp.update { manageUser.getIsFirstTimeUseApp() }
        }
    }
}