plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.compose)
}

android {
    namespace = "com.moviearchive.app.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.moviearchive.app.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}

kotlin {
    androidTarget()
    sourceSets {
        dependencies {
            implementation(compose.runtime)
            implementation(libs.androidx.activity.compose)
        }
    }
}