package com.freshtawi.tawi.di

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.freshtawi.tawi.data.network.api.WeatherApi
import com.freshtawi.tawi.data.util.Environment
import com.google.firebase.crashlytics.BuildConfig
import java.util.Date
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val apiModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(get<Environment>().url)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single {
        OkHttpClient.Builder().apply {
            readTimeout(60, TimeUnit.SECONDS)
            connectTimeout(60, TimeUnit.SECONDS)
            getAll<Interceptor>()
                .forEach {
                    addInterceptor(it)
                }
        }.build()
    }

    single {
        Moshi.Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter())
            .add(NullToEmptyStringAdapter)
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single { get<Retrofit>().create(WeatherApi::class.java) }

    single {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.BASIC
            }
        }
    } bind Interceptor::class

    single { Dispatchers.IO }

    single { MoshiConverterFactory.create(get()).asLenient() }


}

// TODO: Some values in the api response are null which caused the app to crash
object NullToEmptyStringAdapter {
    @FromJson
    fun fromJson(reader: JsonReader): String {
        if (reader.peek() != JsonReader.Token.NULL) {
            return reader.nextString()
        }
        reader.nextNull<Unit>()
        return ""
    }
}