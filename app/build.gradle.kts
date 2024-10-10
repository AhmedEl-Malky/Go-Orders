plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id("kotlin-kapt")
    kotlin("plugin.serialization") version "2.0.20"
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
//    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.go_orders"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.go_orders"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.text.google.fonts)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
//    Navigation Graph
    implementation("androidx.navigation:navigation-compose:2.8.2")
//    Supabase
    implementation(platform("io.github.jan-tennert.supabase:bom:3.0.0"))
    implementation("io.github.jan-tennert.supabase:postgrest-kt")
    //    implementation("io.github.jan-tennert.supabase:auth-kt")
    //    implementation("io.github.jan-tennert.supabase:realtime-kt")
//    ktor
    implementation("io.ktor:ktor-client-android:3.0.0-rc-1")
    implementation("io.ktor:ktor-client-core:3.0.0-rc-1")
    implementation("io.ktor:ktor-client-logging:3.0.0-rc-1")
    implementation("io.ktor:ktor-client-content-negotiation:3.0.0-rc-1")
//    Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
//    hilt navigation compose
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
//    Coil Compose
    implementation("io.coil-kt.coil3:coil-compose:3.0.0-rc01")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.0.0-rc01")
//    Hilt
//    implementation ("com.google.dagger:hilt-android:2.52")
//    kapt ("com.google.dagger:hilt-compiler:2.52")
}


kapt {
    correctErrorTypes = true
}