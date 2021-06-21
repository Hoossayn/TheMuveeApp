import extension.addBaseDynamicFeatureModuleDependencies
import extension.addInstrumentationTestDependencies
import extension.addUnitTestDependencies

plugins {
    id(Plugin.ANDROID_DYNAMIC_FEATURE_PLUGIN)
    id(Plugin.KOTLIN_ANDROID_PLUGIN)
    id(Plugin.KOTLIN_ANDROID_EXTENSIONS_PLUGIN)
    id(Plugin.KOTLIN_KAPT_PLUGIN)
    id(Plugin.DAGGER_HILT_PLUGIN)

}

android {
    compileSdkVersion(AndroidVersion.COMPILE_SDK_VERSION)
    buildToolsVersion(AndroidVersion.BUILD_TOOL_VERSION)

    defaultConfig {
        applicationId = "com.example.home"
        minSdkVersion(AndroidVersion.MIN_SDK_VERSION)
        targetSdkVersion(AndroidVersion.TARGET_SDK_VERSION)
        versionCode = AndroidVersion.VERSION_CODE
        versionName = AndroidVersion.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release"){
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packagingOptions {
        exclude("META-INF/AL2.0")
    }

    dataBinding.isEnabled = true


   //android.buildFeatures.viewBinding = true

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    api(project(Modules.APP))
    api(project(Modules.AndroidLibrary.CORE))
    api(project(Modules.AndroidLibrary.DOMAIN))
    api("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    api("androidx.legacy:legacy-support-v4:1.0.0")

    addBaseDynamicFeatureModuleDependencies()

    // Support and Widgets
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CONSTRAINT_LAYOUT)
    implementation(Dependencies.RECYCLER_VIEW)
    implementation(Dependencies.VIEWPAGER2)
    implementation(Dependencies.SWIPE_REFRESH_LAYOUT)

    // Glide
    implementation(Dependencies.GLIDE)
    kapt(Dependencies.GLIDE_COMPILER)

    // Unit Tests
    addUnitTestDependencies()
    testImplementation(project(Modules.AndroidLibrary.TEST_UTILS))

    // Instrumentation Tests
    addInstrumentationTestDependencies()
    androidTestImplementation(project(Modules.AndroidLibrary.TEST_UTILS))

}