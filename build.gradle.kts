plugins {
    id("com.redmadrobot.android-config") version "0.15"
    id("com.redmadrobot.publish") version "0.15" apply false
}

redmadrobot {
    android {
        minSdk.set(17)
        targetSdk.set(31)
    }
}
