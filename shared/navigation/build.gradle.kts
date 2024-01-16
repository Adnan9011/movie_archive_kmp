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
                }

                libs.precompose.apply {
                    implementation(core)
                    implementation(viewmodel)
                    implementation(koin)
                }

                libs.koin.apply {
                    implementation(core)
                }
                compose.apply {
                    implementation(runtime)
                    implementation(foundation)
                }
                implementation(libs.touchlab)
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