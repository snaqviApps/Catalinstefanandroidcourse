// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {

    /**
     * below up-gradations to
     *  1. AGP (android gradle plugin): 8.1.2
     *  2. Kotlin language: 1.9.0
     *  3. ksp: 1.9.10-1.0.13
     *  is due to make it work for Dagger2 libraries
     *  so it could use 'ksp' instead of 'kapt' in app/build.gradle
     */
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}