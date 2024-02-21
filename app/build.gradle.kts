import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.example.turistaapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.turistaapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val properties = Properties()
        properties.load(FileInputStream(rootProject.file("local.properties")))

        buildConfigField("String", "MAPS_API_KEY", properties.getProperty("MAPS_API_KEY"))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17

        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.1")
//    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.media3:media3-common:1.1.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Google maps
    implementation("com.google.maps.android:maps-compose:2.11.0")
    implementation("com.google.android.gms:play-services-maps:18.2.0")

    // Location
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.4")

    // livedata compose
    implementation("androidx.compose.runtime:runtime-livedata:1.5.4")
    testImplementation("androidx.arch.core:core-testing:2.2.0")

    // Material extend icons
    implementation("androidx.compose.material:material-icons-extended")

    // Room
    implementation("androidx.room:room-ktx:2.6.0")
    kapt("androidx.room:room-compiler:2.6.0")
    testImplementation("androidx.room:room-testing:2.6.0")

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Retrofit2
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Coil
    implementation("io.coil-kt:coil-compose:2.4.0")

    // kotlin coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")

    // mockk
    testImplementation("io.mockk:mockk:1.12.2")

    // Mock web Server
    testImplementation("com.squareup.okhttp3:mockwebserver:4.9.0")

    // HORIZONTAL PAGER
    implementation("androidx.compose.foundation:foundation:1.5.4")

    // datastore preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // splash screen
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Accompanist-Permissions
    implementation("com.google.accompanist:accompanist-permissions:0.29.1-alpha")

    // ZXING (QR Code)
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")

    // Lottie
    implementation("com.airbnb.android:lottie-compose:6.1.0")

    //App compat
    implementation("androidx.appcompat:appcompat:1.6.1")
    // For loading and tinting drawables on older versions of the platform
    implementation ("androidx.appcompat:appcompat-resources:1.6.1")

    coreLibraryDesugaring ("com.android.tools:desugar_jdk_libs:2.0.4")

    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
}

kapt {
    correctErrorTypes = true
}
