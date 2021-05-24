import extension.addCoreModuleDependencies
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
       // consumerProguardFiles = "consumer-rules.pro"
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

    android.buildFeatures.dataBinding = true


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


    implementation(project(Modules.AndroidLibrary.DOMAIN))
    implementation(project(Modules.AndroidLibrary.DATA))

    addCoreModuleDependencies()

    addUnitTestDependencies()
    testImplementation(project(Modules.AndroidLibrary.TEST_UTILS))

    addInstrumentationTestDependencies()
    androidTestImplementation(project(Modules.AndroidLibrary.TEST_UTILS))
}