// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    val kotlin_version by extra("1.4.32")
    //ext.kotlin_version = "1.4.32"
    repositories{
        google()
        jcenter()
        maven { url = uri("https://plugins.gradle.org/m2/") }
        maven { url = uri("https://jitpack.io") }
    }


    dependencies {
        classpath(Plugin.CLASSPATH_GRADLE)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
        classpath(Plugin.CLASSPATH_DAGGER_HILT)
        classpath(Plugin.CLASSPATH_KTLINT)
        classpath(Plugin.CLASSPATH_NAV_SAFE_ARGS)
        classpath(Plugin.CLASSPATH_MP_CHART)
        classpath("com.android.tools.build:gradle:4.1.3")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

plugins {
    id(Plugin.KTLINT) version PluginVersion.KTLINT_VERSION
    id(Plugin.DETEKT) version PluginVersion.DETEKT_VERSION
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://jitpack.io") }
    }

}


subprojects {

    tasks.withType<Test> {
        maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
    }

    // KtLint
    apply(plugin = Plugin.KTLINT) // Version should be inherited from parent

    // KtLint Configurations

    ktlint {
        debug.set(true)
        verbose.set(true)
        android.set(true)
        outputToConsole.set(true)
    }

    // Detekt
    apply(plugin = Plugin.DETEKT)

    detekt {
        config = files("${project.rootDir}/config/detekt/detekt.yml")
        parallel = true

        reports {
            xml {
                enabled = true
                destination = file("${project.rootDir}/build/reports/detekt_report.xml")
            }
            html {
                enabled = true
                destination = file("${project.rootDir}/build/reports/detekt_report.html")
            }
            txt {
                enabled = true
                destination = file("${project.rootDir}/build/reports/detekt_report.txt")
            }
        }
    }
}
// JVM target applied to all Kotlin tasks across all sub-projects
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

/*
task clean(type: Delete) {
    delete rootProject.buildDir
}*/
