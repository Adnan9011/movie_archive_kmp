plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.sqldelight)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
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

            implementation(libs.napier)
        }
        commonTest.dependencies {
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
    }
}

android {
    namespace = "com.moviearchive.data"
    compileSdk = 34
}

sqldelight {
    databases {
        create("DatabaseSource") {
            packageName.set("com.moviearchive")
        }
    }
}