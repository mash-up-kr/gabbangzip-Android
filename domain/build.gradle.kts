import com.mashup.gabbangzip.buildsrc.AppConfig

plugins {
    id("kotlin")
    alias(libs.plugins.jetbrainsKotlinJvm)
    alias(libs.plugins.kotlin.kapt)
}

java {
    sourceCompatibility = AppConfig.sourceCompatibility
    targetCompatibility = AppConfig.targetCompatibility
}

dependencies {
    // dagger hilt
    implementation(libs.hilt.core)
    kapt(libs.hilt.compiler)
    
    // coroutine
    implementation(libs.coroutine.core)
}