plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>()
    .configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }

android {
    namespace = "com.github.funczz.kotlin.rocket_launcher.android"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.github.funczz.kotlin.rocket_launcher.android"
        minSdk = 28
        targetSdk = 33
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
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    defaultConfig {
        // Replace com.example.android.dagger with your class path.
        testInstrumentationRunner = "android_test.HiltJUnitRunner"
    }
}

/**
 * kotlin-rocket-launcher-core
 */
dependencies {
    implementation("com.github.funczz:rocket-launcher-core:0.2.0")
}

dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.activity:activity-ktx:1.7.2")
    //androidx.work
    implementation("androidx.work:work-runtime:2.8.1")
    androidTestImplementation("androidx.work:work-runtime:2.8.1")
    implementation("androidx.work:work-runtime-ktx:2.8.1")
    androidTestImplementation("androidx.work:work-runtime-ktx:2.8.1")
    //androidx.hilt
    //implementation("androidx.hilt:hilt-common:1.0.0")
    implementation("androidx.hilt:hilt-work:1.0.0")
    androidTestImplementation("androidx.hilt:hilt-work:1.0.0")
    implementation("androidx.work:work-testing:2.8.1")
    androidTestImplementation("androidx.work:work-testing:2.8.1")
    implementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:runner:1.5.2")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    kaptTest("androidx.hilt:hilt-compiler:1.0.0")
    kaptAndroidTest("androidx.hilt:hilt-compiler:1.0.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("androidx.constraintlayout:constraintlayout-core:1.0.4")
    implementation("androidx.test.ext:junit-ktx:1.1.5")

    /**
     * Robolectric
     */
    testImplementation("org.robolectric:robolectric:4.10.3")

    /**
     * ORMLite Android
     */
    val ormliteVersion = "6.1"
    implementation("com.j256.ormlite:ormlite-android:$ormliteVersion")
    //implementation("com.j256.ormlite:ormlite-core:$ormliteVersion")

    /**
     * Hilt
     */
    val hiltVersion = "2.44"
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    androidTestImplementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")
    kaptTest("com.google.dagger:hilt-android-compiler:$hiltVersion")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:$hiltVersion")
    testImplementation("com.google.dagger:hilt-android-testing:$hiltVersion")
    androidTestImplementation("com.google.dagger:hilt-android-testing:$hiltVersion")

    /**
     * ETC.
     */
    implementation("org.apache.commons:commons-lang3:3.13.0")
    implementation("org.apache.commons:commons-text:1.10.0")
    implementation("org.jsoup:jsoup:1.16.1")
    implementation("com.google.code.gson:gson:2.10.1")
    val mockkVersion = "1.11.0"
    testImplementation("io.mockk:mockk-android:$mockkVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("org.mockito:mockito-core:5.5.0")
    testImplementation("org.mockito:mockito-inline:5.2.0")
}