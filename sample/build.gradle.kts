plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    compileSdk = 31
    defaultConfig {
        minSdk = 21
        targetSdk = 31

        versionName = "1.0"
        versionCode = 1
    }
}

dependencies {
    implementation(project(":state-delegator"))

    implementation(kotlin("stdlib-jdk8"))
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation("com.airbnb.android:lottie:4.2.1")
}

repositories {
    mavenCentral()
    google()
}
