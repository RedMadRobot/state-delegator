import com.redmadrobot.build.dsl.*

plugins {
    id("com.redmadrobot.android-library")
    id("com.redmadrobot.publish")
}

group = "com.redmadrobot"
description = "Collection of classes that helps you to manage a screen state"

redmadrobot {
    publishing {
        signArtifacts.set(true)

        pom {
            setGitHubProject("RedMadRobot/state-delegator")

            licenses {
                mit()
            }

            developers {
                developer(id = "avegrv", name = "Alexander Egorov", email = "av.egorov@redmadrobot.com")
            }
        }
    }
}

kotlin {
    explicitApi = null
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation("junit:junit:4.13.2")
    testImplementation("org.robolectric:robolectric:3.1.1")
}

publishing {
    repositories {
        if (credentialsExist("ossrh")) ossrh()
    }
}
