enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "MovieArchiveKMP"
include(":androidApp")
include(":shared")
include(":shared:data")
include(":shared:core")
include(":shared:domain")
include(":shared:ui")
include(":shared:feature")
include(":shared:navigation")
