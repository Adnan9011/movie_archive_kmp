plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.compose)
}

kotlin {
    androidTarget()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "ui"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            compose.apply {
                implementation(runtime)
                implementation(foundation)
                implementation(animation)
                implementation(material3)
            }
        }
        commonTest.dependencies { }
        androidMain.dependencies {
            compose.apply {
                implementation(preview)
                implementation(uiTooling)
            }
        }
    }
}

android {
    namespace = "com.moviearchive.ui"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}