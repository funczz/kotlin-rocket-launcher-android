pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://funczz.github.io/kotlin-fsm") }
        maven { setUrl("https://funczz.github.io/kotlin-sam") }
        maven { setUrl("https://funczz.github.io/kotlin-rocket-launcher-core") }
    }
}

rootProject.name = "Rocket Launcher"
include(":app")
