import org.apache.tools.ant.util.JavaEnvUtils.VERSION_11
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.gradleVersionUpdate)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.realm.gradle.plugin)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dokka)
    alias(libs.plugins.kover)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.google.services)
    alias(libs.plugins.google.firebase.crashlytics)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }


    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        androidMain.dependencies {
            api(libs.androidx.activity.compose)
            api(libs.androidx.appcompat)
            api(libs.androidx.core.ktx)
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.mediapipe.genai.android)
            implementation(libs.bundles.tensorflow)
            implementation(libs.bundles.internal.camerax)
            implementation(libs.bundles.maps)
            implementation(libs.kizitonwose.calender)
            implementation(libs.bundles.accompanist)
            implementation(libs.timber)
            implementation(libs.koin.android)
            implementation(libs.lottie.compose)
            implementation(libs.bundles.retrofit)
            implementation(libs.coil.gf)
            implementation(libs.easycrop)
            implementation(libs.androidx.compose.constraintlayout)
            implementation(libs.firebase.crashlytics)
            implementation(libs.datastore)
            implementation(libs.datastore.preferences)
            implementation(libs.coil.compose.android)
            implementation(libs.firebase.auth)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(libs.compose.image.loader)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            implementation(libs.bundles.koin)
            implementation(libs.koin.compose)
            implementation(libs.bundles.coil.common)
            implementation(libs.bundles.ktor.common)

            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.bundles.ktor.common)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.skie.annotations)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.paging.common)
            implementation(libs.androidx.room.runtime)
            implementation(libs.cache4k)
            implementation(libs.napier)
            implementation(libs.kotlinx.atomicfu)
            api(libs.androidx.datastore.preferences.core)
            api(libs.androidx.datastore.core.okio)
            implementation(libs.okio)
            implementation(libs.androidx.constraintlayout)
            implementation(libs.navigation.compose)
            implementation(libs.realm.library.base)

            api(libs.bundles.moko)
            api(libs.koin.core)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.freshtawi.tawi"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.freshtawi.tawi"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            resources.excludes.add("META-INF/gradle/incremental.annotation.processors")
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    dependencies {
        debugImplementation(compose.uiTooling)

    }

}


dependencies {
    implementation(libs.moko.geo)
    implementation(libs.moko.geo.compose)
    implementation(libs.moko.biometry)
    implementation(libs.moko.biometry.compose)
}




