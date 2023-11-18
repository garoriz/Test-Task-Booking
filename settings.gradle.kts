pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TestTaskBooking"
include(":app")
include(":core")
include(":hotel-feature")
include(":network")
include(":number-feature")
include(":booking-feature")
include(":paid-feature")
