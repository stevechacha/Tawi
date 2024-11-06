package com.freshtawi.tawi.di

import com.freshtawi.tawi.domain.usecases.GetLocationUseCase
import com.freshtawi.tawi.domain.usecases.GetNotificationsUseCase
import com.freshtawi.tawi.domain.usecases.IGetNotificationsUseCase
import com.freshtawi.tawi.domain.usecases.validation.IValidationUseCase
import com.freshtawi.tawi.domain.usecases.validation.ValidationUseCaseUseCase
import com.freshtawi.tawi.domain.usecases.IGetUserLocationUseCase
import com.freshtawi.tawi.domain.usecases.IManageAuthenticationUseCase
import com.freshtawi.tawi.domain.usecases.IManageSettingUseCase
import com.freshtawi.tawi.domain.usecases.ManageAuthenticationUseCase
import com.freshtawi.tawi.domain.usecases.ManageSettingUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val useCaseModule = module {
    singleOf(::ManageAuthenticationUseCase) { bind<IManageAuthenticationUseCase>() }
    singleOf(::ManageSettingUseCase) { bind<IManageSettingUseCase>() }
    singleOf(::ValidationUseCaseUseCase) { bind<IValidationUseCase>() }
    singleOf(::GetLocationUseCase) { bind<IGetUserLocationUseCase>() }
    singleOf(::GetNotificationsUseCase) { bind<IGetNotificationsUseCase>() }
}
