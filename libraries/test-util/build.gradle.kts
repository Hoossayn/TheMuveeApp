plugins {
    id(Plugin.ANDROID_LIBRARY_PLUGIN)
    id(Plugin.KOTLIN_ANDROID_PLUGIN)
    id(Plugin.KOTLIN_ANDROID_EXTENSIONS_PLUGIN)
    id(Plugin.KOTLIN_KAPT_PLUGIN)
}

android {
    compileSdkVersion(AndroidVersion.COMPILE_SDK_VERSION)
    buildToolsVersion(AndroidVersion.BUILD_TOOL_VERSION)

    defaultConfig {
        minSdkVersion(AndroidVersion.MIN_SDK_VERSION)
        targetSdkVersion(AndroidVersion.TARGET_SDK_VERSION)
        versionCode = AndroidVersion.VERSION_CODE
        versionName = AndroidVersion.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.ANDROIDX_CORE_KTX)


    implementation(Dependencies.COROUTINES_CORE)
    implementation(Dependencies.COROUTINES_ANDROID)

    // Lifecycle, LiveData, ViewModel
    implementation(Dependencies.LIFECYCLE_LIVEDATA_KTX)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Dependencies.LIFECYCLE_EXTENSIONS)


    implementation(TestDeps.JUNIT5_API)
    implementation(TestDeps.JUNIT5_ENGINE)

    // GSon
    implementation(Dependencies.GSON)

    // Coroutines Test
    implementation(TestDeps.COROUTINES_TEST)

    // MockWebServer
    implementation(TestDeps.MOCK_WEB_SERVER)
}