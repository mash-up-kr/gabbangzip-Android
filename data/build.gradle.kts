import com.mashup.gabbangzip.sharedalbum.buildsrc.AppConfig

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.room)
}

android {
    namespace = AppConfig.dataNameSpace
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk

        testInstrumentationRunner = AppConfig.testRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = AppConfig.sourceCompatibility
        targetCompatibility = AppConfig.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = AppConfig.jvmTarget
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // dagger hilt
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    // network
    implementation(libs.bundles.network)
    ksp(libs.moshi.code.gen)

    // coroutine
    implementation(libs.bundles.coroutine)
}
