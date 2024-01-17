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
            baseName = "di"
            isStatic = true
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                projects.shared.apply {
                    implementation(data)
                    implementation(domain)
                    implementation(feature)
                    implementation(navigation)
                    implementation(core)
                }

                libs.koin.apply {
                    implementation(core)
                    implementation(compose)
                }

                libs.precompose.apply {
                    implementation(core)
                    implementation(viewmodel)
                    implementation(koin)
                }

                libs.coil.apply {
                    implementation(core)
                    implementation(compose)
                    implementation(network)
                }
                libs.ktor.apply {
                    implementation(core)
                }

                implementation(compose.foundation)
            }
        }
        androidMain {
            dependencies {
                implementation(libs.koin.android)
            }
        }
    }
}

android {
    namespace = "com.moviearchive.di"
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