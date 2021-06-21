import extension.addAppModuleDependencies
import extension.addInstrumentationTestDependencies
import extension.addUnitTestDependencies


plugins {
    id(Plugin.ANDROID_APPLICATION_PLUGIN)
    id(Plugin.KOTLIN_ANDROID_PLUGIN)
    id(Plugin.KOTLIN_ANDROID_EXTENSIONS_PLUGIN)
    id(Plugin.KOTLIN_KAPT_PLUGIN)
    id(Plugin.DAGGER_HILT_PLUGIN)
    id(Plugin.NAVIGATION_SAFE_ARGS)
}


android {

    lintOptions {
        isCheckReleaseBuilds = false
    }

    compileSdkVersion(AndroidVersion.COMPILE_SDK_VERSION)
    buildToolsVersion(AndroidVersion.BUILD_TOOL_VERSION)

    defaultConfig {
        applicationId = (AndroidVersion.APPLICATION_ID)
        minSdkVersion(AndroidVersion.MIN_SDK_VERSION)
        targetSdkVersion(AndroidVersion.TARGET_SDK_VERSION)
        versionCode = AndroidVersion.VERSION_CODE
        versionName = AndroidVersion.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }


        kapt{
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }

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
        exclude("**/attach_hotspot_windows.dll")
        exclude("META-INF/licenses/**")
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
    }

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions{
        jvmTarget = "1.8"
    }

    dynamicFeatures = mutableSetOf(
        Modules.DynamicFeature.HOME,
        Modules.DynamicFeature.MOVIE_DETAILS,
        Modules.DynamicFeature.SHOWS,

        /*,
        Modules.DynamicFeature.SHOWS_DETAILS*/
    )

}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    api(project(Modules.AndroidLibrary.DATA))
    api(project(Modules.AndroidLibrary.DOMAIN))
    api(project(Modules.AndroidLibrary.CORE))
    //implementation("com.google.android.play:core:1.10.0")

    // For Kotlin users also import the Kotlin extensions library for Play Core:

    addAppModuleDependencies()

    // Unit Tests
    addUnitTestDependencies()
    testImplementation(project(Modules.AndroidLibrary.TEST_UTILS))

    // Instrumentation Tests
    addInstrumentationTestDependencies()
    androidTestImplementation(project(Modules.AndroidLibrary.TEST_UTILS))
}