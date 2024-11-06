package com.freshtawi.tawi.di

import com.freshtawi.tawi.app.AppScreenModel
import com.freshtawi.tawi.presentation.screen.account.changeLanguage.ChangeLanguageViewModel
import com.freshtawi.tawi.presentation.screen.alerts.NotificationScreenModel
import com.freshtawi.tawi.presentation.screen.auth.login.LoginViewModel
import com.freshtawi.tawi.presentation.screen.auth.pickLanguage.PickLanguageViewModel
import com.freshtawi.tawi.presentation.screen.auth.registration.RegistrationViewModel
import com.freshtawi.tawi.presentation.screen.auth.registration.tabs.loginDetail.LoginDetailsViewModel
import com.freshtawi.tawi.presentation.screen.auth.registration.tabs.personalDetail.PersonalDetailsViewModel
import com.freshtawi.tawi.presentation.screen.calender.weather.WeatherViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val screenModel = module {
    factoryOf(::PickLanguageViewModel)
    factoryOf(::ChangeLanguageViewModel)
    factoryOf(::WeatherViewModel)
    factoryOf(::NotificationScreenModel)
    factoryOf(::LoginDetailsViewModel)
    factoryOf(::LoginViewModel)
    factoryOf(::RegistrationViewModel)
    factoryOf(::LoginViewModel)
    factoryOf(::LoginDetailsViewModel)
    factoryOf(::PersonalDetailsViewModel)
    factoryOf(::AppScreenModel)

}