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
        minSdkVersion(AndroidVersion.MIN_SDK_VERSION)
        targetSdkVersion(AndroidVersion.TARGET_SDK_VERSION)
        versionCode = AndroidVersion.VERSION_CODE
        versionName = AndroidVersion.VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        //consumerProguardFiles "consumer-rules.pro"
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

}

dependencies {

    implementation(project(Modules.AndroidLibrary.DATA))

    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.ANDROIDX_CORE_KTX)

    // Dagger
    implementation(Dependencies.DAGGER_HILT_ANDROID)
    kapt(Dependencies.DAGGER_HILT_COMPILER)


    // Coroutines
    implementation(Dependencies.COROUTINES_CORE)
    implementation(Dependencies.COROUTINES_ANDROID)

    testImplementation(project(Modules.AndroidLibrary.TEST_UTILS))

    testImplementation(Dependencies.GSON)

    addUnitTestDependencies()
    addInstrumentationTestDependencies()
}