plugins {
    id("com.redmadrobot.android-library")
    id("com.redmadrobot.publish")
}

kotlin {
    explicitApi = null
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation("junit:junit:4.13.2")
    testImplementation("org.robolectric:robolectric:3.1.1")
}
