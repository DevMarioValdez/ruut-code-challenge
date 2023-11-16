plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "mx.mariovaldez.ruutcodechallenge"
    compileSdk = 34

    defaultConfig {
        applicationId = "mx.mariovaldez.ruutcodechallenge"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://api.iex.cloud/v1/\"")
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", "\"https://api.iex.cloud/v1/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    applicationVariants.all {
        val variant = this
        variant.outputs
            .map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
            .forEach { output ->
                val outputFileName =
                    "$applicationId - ${variant.baseName} - ${variant.versionName} - v${variant.versionCode}.apk"
                output.outputFileName = outputFileName
            }
    }

}

dependencies {
    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_version}")

    // AndroidX Core
    implementation("androidx.core:core-ktx:${Versions.androidx_core_version}")
    implementation("androidx.activity:activity-ktx:${Versions.androidx_activity_version}")
    implementation("androidx.fragment:fragment-ktx:${Versions.androidx_fragment_version}")

    // AndroidX Appcompat
    implementation("androidx.appcompat:appcompat:${Versions.androidx_app_compat_version}")

    // Encrypted Shared Preferences
    implementation("androidx.security:security-crypto:${Versions.androidx_encrypted_shared_preferences_version}")

    // Hilt
    //noinspection GradleDependency
    implementation("com.google.dagger:hilt-android:${Versions.hilt_version}")
    //implementation "com.google.dagger:hilt-android-compiler:$hilt_version"

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle_saved_state_version}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_version}")
    implementation("androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle_version}")

    // Retrofit & Gson
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofit_version}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor_version}")

    // Constraint Layout
    implementation("androidx.constraintlayout:constraintlayout:${Versions.androidx_constraint_layout_version}")

    // Material Components
    implementation("com.google.android.material:material:${Versions.google_material_components_version}")

    //Timber
    implementation("com.jakewharton.timber:timber:${Versions.timber_version}")

    //Coil
    implementation("io.coil-kt:coil:${Versions.coil_version}")

    implementation("javax.annotation:javax.annotation-api:1.3.2")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlin_coroutines_version}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlin_coroutines_version}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutines_play_services_version}")

    //Shimmer
    implementation("com.facebook.shimmer:shimmer:${Versions.shimmer_version}")

    // Kotlin
    implementation("androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}")
    implementation("androidx.navigation:navigation-ui-ktx:${Versions.nav_version}")

    implementation("androidx.test.ext:junit-ktx:1.1.5")

    //Room

    implementation("androidx.room:room-runtime:${Versions.room_version}")
    annotationProcessor("androidx.room:room-compiler:${Versions.room_version}")

    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:${Versions.room_version}")
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:${Versions.room_version}")


    //JWT
    api("io.jsonwebtoken:jjwt-api:0.10.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.10.5")
    runtimeOnly("io.jsonwebtoken:jjwt-orgjson:0.10.5") { }

    //MPAndroid Chart
    implementation("com.github.PhilJay:MPAndroidChart:${Versions.MPAndroidChart}")

    // region Dependencies - Annotation Processor
    // Hilt
    kapt("com.google.dagger:hilt-android-compiler:${Versions.hilt_version}")

    // endregion

    testImplementation("junit:junit:${Versions.junit_version}")
    androidTestImplementation("androidx.test.ext:junit:${Versions.test_ext_junit_version}")

    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.test_expresso_core_version}")
    androidTestImplementation("androidx.test:runner:${Versions.test_runner_version}")
    androidTestImplementation("androidx.test:rules:${Versions.test_rules_version}")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:${Versions.test_expresso_contrib_version}")
    //end region
}

if (project.hasProperty("kapt")) {
    kapt {
        correctErrorTypes = true
        javacOptions {
            option("-source", "9")
            option("-target", "9")
            option("-release", "9")
        }
    }
}
