plugins {
    // Android plugin must be before multiplatform plugin until https://youtrack.jetbrains.com/issue/KT-34038 is fixed.
    alias(libs.plugins.android.application)
    kotlin("multiplatform")
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.compose)
}

kotlin {
    explicitApi()
    jvmToolchain(libs.versions.jvm.toolchain.get().toInt())

    androidTarget()

    sourceSets {
        androidMain.dependencies {
            implementation(projects.library)
            implementation(libs.bundles.compose)
        }
    }
}

android {
    compileSdk = libs.versions.android.compile.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.min.get().toInt()
        targetSdk = libs.versions.android.target.get().toInt()
    }
    namespace = "com.juul.datadog.sample.android"
    lint {
        abortOnError = true
        warningsAsErrors = true
        disable += listOf("AndroidGradlePluginVersion", "GradleDependency", "MissingApplicationIcon")
    }
    buildFeatures.compose = true
}
