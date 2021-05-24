import extension.addInstrumentationTestDependencies
import extension.addUnitTestDependencies

plugins {
    id(Plugin.ANDROID_LIBRARY_PLUGIN)
    id(Plugin.KOTLIN_ANDROID_PLUGIN)
    id(Plugin.KOTLIN_ANDROID_EXTENSIONS_PLUGIN)
    id(Plugin.KOTLIN_KAPT_PLUGIN)
    id(Plugin.DAGGER_HILT_PLUGIN)
}

android {
    compileSdkVersion (AndroidVersion.COMPILE_SDK_VERSION)
    buildToolsVersion (AndroidVersion.BUILD_TOOL_VERSION)

    defaultConfig {
        minSdkVersion (AndroidVersion.MIN_SDK_VERSION)
        targetSdkVersion (AndroidVersion.TARGET_SDK_VERSION)
        versionCode = AndroidVersion.VERSION_CODE
        versionName = AndroidVersion.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        //consumerProguardFiles = "consumer-rules.pro"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    sourceSets {

        val sharedTestDir =
            "${project(Modules.AndroidLibrary.TEST_UTILS).projectDir}/src/test-shared/java"

        getByName("test") {
//            java.srcDir(sharedTestDir)
            resources.srcDir(
                "${project(Modules.AndroidLibrary.TEST_UTILS).projectDir}" +
                        "/src/test/resources"
            )
        }

        getByName("androidTest") {
//            java.srcDir(sharedTestDir)
            resources.srcDir(
                "${project(Modules.AndroidLibrary.TEST_UTILS).projectDir}" +
                        "/src/test/resources"
            )
        }
    }

    configurations.all {
        resolutionStrategy {
            exclude("org.jetbrains.kotlinx", "kotlinx-coroutines-debug")
        }
    }

    packagingOptions {
        exclude("META-INF/LICENSE.md")
        exclude("META-INF/LICENSE-notice.md")
    }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.ANDROIDX_CORE_KTX)

    // Dagger
    implementation(Dependencies.DAGGER_HILT_ANDROID)
    kapt(Dependencies.DAGGER_HILT_COMPILER)

    // Room
    implementation(Dependencies.ROOM_RUNTIME)
    // For Kotlin use kapt instead of annotationProcessor
    kapt(Dependencies.ROOM_COMPILER)
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation(Dependencies.ROOM_KTX)
    // optional - RxJava support for Room


    // Coroutines
    implementation(Dependencies.COROUTINES_CORE)
    implementation(Dependencies.COROUTINES_ANDROID)

    // Retrofit
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_GSON_CONVERTER)
    implementation(Dependencies.RETROFIT_RX_JAVA3_ADAPTER)
    // change base url runtime
    implementation(Dependencies.RETROFIT_URL_MANAGER)
    // Gson
    implementation(Dependencies.GSON)
    // Chucker
    implementation(Dependencies.CHUCKER_DEBUG)

    addUnitTestDependencies()
    testImplementation(project(Modules.AndroidLibrary.TEST_UTILS))

    addInstrumentationTestDependencies()
    androidTestImplementation(project(Modules.AndroidLibrary.TEST_UTILS))
}