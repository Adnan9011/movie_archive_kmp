plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.sqldelight)
}

kotlin {
    androidTarget()
    js(IR) {
        browser()
    }
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "data"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            projects.shared.apply {
                implementation(core)
            }

            implementation(libs.kotlinx.serialization)

            implementation(libs.koin.core)

            libs.ktor.apply {
                implementation(core)
                implementation(serialization)
                implementation(negotiation)
                implementation(logging)
            }

            implementation(libs.sqldelight.coroutines)

            implementation(libs.datastore)

            implementation(libs.napier)
        }

        androidMain.dependencies {
            implementation(libs.ktor.okhttp)

            implementation(libs.sqldelight.android)

            implementation(libs.koin.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.darwin)

            implementation(libs.sqldelight.native)
        }
        jsMain.dependencies {
            // JS specific dependencies can be added here
        }
    }
}

android {
    namespace = "com.moviearchive.data"
    compileSdk = libs.versions.app.compile.sdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.app.min.sdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(libs.versions.java.jdk.get().toInt())
    }
}

sqldelight {
    databases {
        create("DatabaseSource") {
            packageName.set("com.moviearchive")
        }
    }
}