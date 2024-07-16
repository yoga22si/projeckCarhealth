buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.3.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
        classpath("com.google.gms:google-services:4.4.2") // Add this line
    }
}

plugins {
    // Other plugins
    id("com.google.gms.google-services") version "4.4.2" apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
}


