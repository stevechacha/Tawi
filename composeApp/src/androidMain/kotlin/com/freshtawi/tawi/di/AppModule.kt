package com.freshtawi.tawi.di

import org.koin.dsl.module

fun appModule() = module {
    includes(
        screenModel,
        apiModule,
        networkModule,
        localStorageModule,
        repoModule,
        useCaseModule,
        enviromentModule,
        locationModule,
        firebaseModule()
    )
}
