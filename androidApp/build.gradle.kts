plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.compose)
}

android {
    namespace = "com.moviearchive.app.android"
    compileSdk = libs.versions.app.compile.sdk.get().toInt()
    defaultConfig {
        applicationId = "com.moviearchive.app.android"
        minSdk = libs.versions.app.min.sdk.get().toInt()
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(libs.versions.java.jdk.get().toInt())
    }
}

kotlin {
    androidTarget()
    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.runtime)
                implementation(libs.voyager.navigator)
                projects.shared.apply {
                    implementation(navigation)
                }
            }
        }

        androidMain {
            dependencies {
                implementation(libs.androidx.activity.compose)
            }
        }
    }
}