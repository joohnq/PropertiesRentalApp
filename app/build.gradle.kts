plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")

    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.joohnq.propertiesrentalapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.joohnq.propertiesrentalapp"
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
    val koin_version = "3.5.3"
    val coroutines_version = "1.7.3"
    val okhttp_version = "4.12.0"
    val navigation_compose_version = "2.7.7"
    val compose_bom_version = "2024.02.02"
    val hilt_version = "2.51"
    val hilt_navigation_version = "1.2.0"

    implementation("io.coil-kt:coil-compose:2.4.0")

    implementation("com.google.accompanist:accompanist-permissions:0.28.0")

    implementation("com.google.android.gms:play-services-location:21.2.0")

    // Splash API
    implementation("androidx.core:core-splashscreen:1.0.1")

    //Loader
    implementation("com.github.commandiron:ComposeLoading:1.0.4")

    //FIREBASE
    implementation(platform("com.google.firebase:firebase-bom:32.7.4"))
    implementation("com.google.firebase:firebase-firestore")
    implementation(platform("androidx.compose:compose-bom:$compose_bom_version"))
    implementation("androidx.navigation:navigation-compose:$navigation_compose_version")
    androidTestImplementation(platform("androidx.compose:compose-bom:$compose_bom_version"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.android.gms:play-services-auth:21.0.0")
    implementation("com.google.firebase:firebase-analytics")

    //OkHTTP
    implementation("com.squareup.okhttp3:okhttp:$okhttp_version")
    implementation(platform("com.squareup.okhttp3:okhttp-bom:$okhttp_version"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    //COROUTINES
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")

    //HILT
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-compiler:$hilt_version")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:$compose_bom_version"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:$compose_bom_version"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}