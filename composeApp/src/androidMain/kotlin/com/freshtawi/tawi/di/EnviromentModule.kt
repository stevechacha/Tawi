package com.freshtawi.tawi.di

import com.freshtawi.tawi.data.util.Environment
import org.koin.dsl.module

val enviromentModule = module {
    single<Environment> { Environment.Main }
}