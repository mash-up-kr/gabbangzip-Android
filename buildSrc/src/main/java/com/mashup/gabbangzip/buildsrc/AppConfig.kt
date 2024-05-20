package com.mashup.gabbangzip.buildsrc

import org.gradle.api.JavaVersion

object AppConfig {
    const val compileSdk = 34
    const val minSdk = 26
    const val targetSdk = 34
    const val kotlinCompilerExtension = "1.5.1"
    
    const val applicationId = "com.mashup.gabbangzip.shared_album"
    const val appNameSpace = "com.mashup.gabbangzip.shared_album"
    
    const val appVersionCode = 1
    const val appVersionName = "1.0"
    
    const val dataNameSpace = "com.mashup.gabbangzip.data"
    const val domainNameSpace = "com.mashup.gabbangzip.domain"
    const val presentationNameSpace = "com.mashup.gabbangzip.presentation"
    
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
    
    val sourceCompatibility = JavaVersion.VERSION_17
    val targetCompatibility = JavaVersion.VERSION_17
    val jvmTarget = JavaVersion.VERSION_17.toString()
}