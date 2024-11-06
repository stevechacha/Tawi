package com.freshtawi.tawi.di


import com.freshtawi.tawi.domain.repo.INotificationGateway
import com.freshtawi.tawi.data.fake.FakeNotificationGateway
import com.freshtawi.tawi.domain.repo.IUserGateway
import com.freshtawi.tawi.data.repo.UserGateway
import com.freshtawi.tawi.data.repo.WeatherRepositoryImpl
import com.freshtawi.tawi.database.ILocalConfigurationGateway
import com.freshtawi.tawi.domain.repo.WeatherRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.freshtawi.tawi.database.LocalConfigurationGateway


val repoModule = module {
    singleOf(::WeatherRepositoryImpl) { bind<WeatherRepository>() } // fake
    singleOf(::LocalConfigurationGateway) { bind<ILocalConfigurationGateway>() } // local
    singleOf(::FakeNotificationGateway) { bind<INotificationGateway>() } // fake
    singleOf(::LocalConfigurationGateway) { bind<ILocalConfigurationGateway>() }   // local
    singleOf(::UserGateway) { bind<IUserGateway>() } // remote
}