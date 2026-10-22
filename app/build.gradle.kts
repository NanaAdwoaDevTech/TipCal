plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.tipcal"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.tipcal"   // change to your own unique ID before publishing
        minSdk = 24
        targetSdk = 35                         // raise if Play Console requires a newer level
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
            // Add a signingConfig here, or use Play App Signing and upload as-is.
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
}
