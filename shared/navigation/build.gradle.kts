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
            baseName = "navigation"
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
                    implementation(core)
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
                libs.koin.apply {
                    implementation(core)
                    implementation(compose)
                }
                compose.apply {
                    implementation(runtime)
                    implementation(foundation)
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
    namespace = "com.moviearchive.navigation"
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