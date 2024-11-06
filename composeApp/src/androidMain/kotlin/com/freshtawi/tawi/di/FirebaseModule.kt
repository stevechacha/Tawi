package com.freshtawi.tawi.di

import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import org.koin.dsl.module

fun firebaseModule() = module {
    single { FirebaseAuth.getInstance() }
    single { Gson() }

}
