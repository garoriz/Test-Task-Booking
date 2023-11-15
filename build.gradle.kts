// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.android.dynamic-feature") version "8.1.2" apply false
    id("com.android.library") version "8.1.2" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.21")
    }
}

tasks.register("clean").configure {
    delete("build")
}