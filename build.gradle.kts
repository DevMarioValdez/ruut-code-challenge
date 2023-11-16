import org.jetbrains.kotlin.gradle.plugin.extraProperties

// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        mavenCentral()
    }
    dependencies {

        classpath("com.android.tools.build:gradle:8.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
    }

}
plugins {
    id("org.jlleitschuh.gradle.ktlint-idea") version "11.3.2"
    id("com.android.application") version "8.1.3" apply false
    id("com.android.library") version "8.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}

allprojects {
    apply( plugin =  "org.jlleitschuh.gradle.ktlint")
}

subprojects {
    apply( plugin = "org.jlleitschuh.gradle.ktlint") // Version should be inherited from parent

    // Optionally configure plugin
    //ktlint {
      //  debug = true
    //}
}

