package com.freshtawi.tawi.di

import com.freshtawi.tawi.database.UserConfigurationCollection
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.dsl.module

val localStorageModule = module {

    single {
        RealmConfiguration.Builder(
            schema = setOf(
                UserConfigurationCollection::class,
            )
        ).compactOnLaunch().deleteRealmIfMigrationNeeded().build()
    }
    single { Realm.open(configuration = get<RealmConfiguration>()) }
}