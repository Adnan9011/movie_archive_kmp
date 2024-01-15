plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
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
                }

                libs.koin.apply {
                    implementation(core)
                }
            }
        }
    }
}

android {
    namespace = "com.moviearchive.di"
    compileSdk = 34

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}