// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    dependencies {

        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
        classpath("org.jlleitschuh.gradle:ktlint-gradle:12.0.2")
    }
    repositories {
        mavenCentral()
    }
}

plugins {
    id("com.android.application") version "8.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.android.library") version "8.1.3" apply false

    // ktlint
    id("org.jlleitschuh.gradle.ktlint") version "12.0.2"
}
