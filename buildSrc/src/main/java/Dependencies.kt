/* (c) Helios Software Developer. All rights reserved. */
package dependencies

/**
 * File that contains object used for dependencies
 *
 * @author Ruel N. Grajo on 01/15/2020.
 */

// Object that is used for dependency version
object Versions {

    // build
    const val minSdk = 18
    const val compileSdk = 29
    const val targetSdk = 29
    const val buildTools = "29.0.2"

    // kotlin
    const val kotlin = "1.3.61"

    // android
    const val appcompat = "1.1.0"
    const val androidGradle = "3.5.3"
    const val constraintLayout = "1.1.3"
    const val lifecycle = "2.1.0"
    const val coreKtx = "1.0.0"
    const val room = "2.2.3"

    // google
    const val dagger = "2.23.2"
    const val materialDesign = "1.2.0-alpha03"

    // test
    const val testRunner = "1.2.0"
    const val androidxTest = "3.2.0"
    const val jUnit = "4.12"

    // third parties
    const val rxJavaVersion = "2.2.12"
    const val rxAndroidVersion = "2.1.1"

    // network
    const val retrofit = "2.5.0"
    const val fresco = "2.0.0"
}

// Object that contains libraries needed by the app
object Libs {

    private object Kotlin {
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    }

    private object Android {
        const val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradle}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

        const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
        const val lifecycleViewModelktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"

        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"

        const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
        const val roomRxJava = "androidx.room:room-rxjava2:${Versions.room}"
        const val roomGuava = "androidx.room:room-guava:${Versions.room}"
        const val roomTesting = "androidx.room:room-testing:${Versions.room}"
    }

    private object Dagger {
        const val core = "com.google.dagger:dagger:${Versions.dagger}"
        const val android = "com.google.dagger:dagger-android:${Versions.dagger}"
        const val androidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
        const val processor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
        const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    }

    // Section for third-party dependencies
    private object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitGSONConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val retrofitRxAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"

        const val fresco = "com.facebook.fresco:fresco:${Versions.fresco}"
    }

    private object RxJava {
        const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJavaVersion}"
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"
    }

    private object Test {
        const val jUnit = "junit:junit:${Versions.jUnit}"
        const val testRunner = "androidx.test:runner:${Versions.testRunner}"
        const val androidxTestCore = "androidx.test.espresso:espresso-core:${Versions.androidxTest}"
    }
    // End of section for third-party dependencies
}