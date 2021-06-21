package extension

import Dependencies
import TestDeps
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.addAppModuleDependencies(){

    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.ANDROIDX_CORE_KTX)
    implementation(Dependencies.ANDROIDX_FRAGMENT_KTX)


    // Support and Widgets
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CONSTRAINT_LAYOUT)
    implementation(Dependencies.RECYCLER_VIEW)
    implementation(Dependencies.VIEWPAGER2)
    implementation(Dependencies.SWIPE_REFRESH_LAYOUT)

    // Views, Animations
    implementation(Dependencies.LOTTIE)

    // Lifecycle, LiveData, ViewModel
    implementation(Dependencies.LIFECYCLE_LIVEDATA_KTX)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL_SAVEDSTATE)
    implementation(Dependencies.LIFECYCLE_COMMON_JAVA8)
    implementation(Dependencies.LIFECYCLE_SERVICE)
    implementation(Dependencies.LIFECYCLE_PROCESS)


    // Navigation Components
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.NAVIGATION_RUNTIME)
    implementation(Dependencies.NAVIGATION_DYNAMIC)

    // Dagger Hilt
    implementation(Dependencies.DAGGER_HILT_ANDROID)
    kapt(Dependencies.DAGGER_HILT_COMPILER)
    // Dagger Hilt AndroidX & ViewModel
    implementation(Dependencies.DAGGER_HILT_VIEWMODEL)
    kapt(Dependencies.DAGGER_HILT_ANDROIDX_HILT_COMPILER)

    // RxJava
    //implementation(Dependencies.RX_JAVA3)
    // RxAndroid
    //implementation(Dependencies.RX_JAVA3_ANDROID)

    // Coroutines
    implementation(Dependencies.COROUTINES_CORE)
    implementation(Dependencies.COROUTINES_ANDROID)

    // Leak Canary
//    debugImplementation(Dependencies.LEAK_CANARY)

    // Room
    implementation(Dependencies.ROOM_RUNTIME)
    // For Kotlin use kapt instead of annotationProcessor
    kapt(Dependencies.ROOM_COMPILER)
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation(Dependencies.ROOM_KTX)
    // optional - RxJava support for Room
    //implementation(Dependencies.ROOM_RXJAVA3)

    // Retrofit
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_GSON_CONVERTER)
    implementation(Dependencies.RETROFIT_RX_JAVA3_ADAPTER)
    // change base url runtime
    implementation(Dependencies.RETROFIT_URL_MANAGER)
    // Gson
    implementation(Dependencies.GSON)
    implementation(Dependencies.CHUCKER_DEBUG)
//  implementation(Dependencies.CHUCKER_RELEASE_NO_OP)

    // Glide
    implementation(Dependencies.GLIDE)
    kapt(Dependencies.GLIDE_COMPILER)

    //PLAY CORE
    //implementation(Dependencies.PLAY_CORE_KTX)
}


/**
 * Adds dependencies to core module
 */
fun DependencyHandler.addCoreModuleDependencies() {
    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.ANDROIDX_CORE_KTX)
    implementation(Dependencies.ANDROIDX_FRAGMENT_KTX)

    // Support and Widgets
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CONSTRAINT_LAYOUT)
    implementation(Dependencies.RECYCLER_VIEW)
    implementation(Dependencies.VIEWPAGER2)
    implementation(Dependencies.SWIPE_REFRESH_LAYOUT)

    // Lifecycle, LiveData, ViewModel
    implementation(Dependencies.LIFECYCLE_LIVEDATA_KTX)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL_SAVEDSTATE)
    implementation(Dependencies.LIFECYCLE_COMMON_JAVA8)
    implementation(Dependencies.LIFECYCLE_SERVICE)
    implementation(Dependencies.LIFECYCLE_PROCESS)

    // Navigation Components
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.NAVIGATION_RUNTIME)
    implementation(Dependencies.NAVIGATION_DYNAMIC)

    // Dagger Hilt
    implementation(Dependencies.DAGGER_HILT_ANDROID)
    kapt(Dependencies.DAGGER_HILT_COMPILER)
    // Dagger Hilt AndroidX & ViewModel
    implementation(Dependencies.DAGGER_HILT_VIEWMODEL)
    kapt(Dependencies.DAGGER_HILT_ANDROIDX_HILT_COMPILER)

    // RxJava
    //implementation(Dependencies.RX_JAVA3)
    // RxAndroid
    //implementation(Dependencies.RX_JAVA3_ANDROID)

    // Coroutines
    implementation(Dependencies.COROUTINES_CORE)
    implementation(Dependencies.COROUTINES_ANDROID)

    // Glide
    implementation(Dependencies.GLIDE)
    kapt(Dependencies.GLIDE_COMPILER)
}

/**
 * Adds core dependencies such as kotlin, appcompat, navigation and dagger-hilt to Dynamic
 * Feature modules.
 *
 */
fun DependencyHandler.addBaseDynamicFeatureModuleDependencies() {
    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.ANDROIDX_CORE_KTX)
    implementation(Dependencies.ANDROIDX_FRAGMENT_KTX)

    // Lifecycle, LiveData, ViewModel
    implementation(Dependencies.LIFECYCLE_LIVEDATA_KTX)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Dependencies.LIFECYCLE_COMMON_JAVA8)
//    implementation(Dependencies.LIFECYCLE_SERVICE)
//    implementation(Dependencies.LIFECYCLE_PROCESS)

    // Navigation Components
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.NAVIGATION_RUNTIME)
    implementation(Dependencies.NAVIGATION_DYNAMIC)

    // Dagger Hilt
    implementation(Dependencies.DAGGER_HILT_ANDROID)
    kapt(Dependencies.DAGGER_HILT_COMPILER)
    // Dagger Hilt AndroidX & ViewModel
    implementation(Dependencies.DAGGER_HILT_VIEWMODEL)
    kapt(Dependencies.DAGGER_HILT_ANDROIDX_HILT_COMPILER)

    // RxJava
   // implementation(Dependencies.RX_JAVA3)
    // RxAndroid
  //  implementation(Dependencies.RX_JAVA3_ANDROID)

    // Coroutines
    implementation(Dependencies.COROUTINES_CORE)
    implementation(Dependencies.COROUTINES_ANDROID)
}

/**
 * Adds Unit test dependencies
 */
fun DependencyHandler.addUnitTestDependencies() {

    // (Required) Writing and executing Unit Tests on the JUnit Platform
    testImplementation(TestDeps.JUNIT5_API)
    testRuntimeOnly(TestDeps.JUNIT5_ENGINE)

    // (Optional) If you need "Parameterized Tests"
    testImplementation(TestDeps.JUNIT5_PARAMS)

    testImplementation(TestDeps.ANDROIDX_CORE_TESTING)
    testImplementation(TestDeps.ROBOLECTRIC)

    // AndroidX Test - JVM testing
    testImplementation(TestDeps.ANDROIDX_TEST_CORE_KTX)
//    testImplementation(TestDeps.ANDROIDX_JUNIT)

    // Coroutines Test
    testImplementation(TestDeps.COROUTINES_TEST)

    // MockWebServer
    testImplementation(TestDeps.MOCK_WEB_SERVER)

    // Gson
    testImplementation(TestDeps.GSON)

    // MockK
    testImplementation(TestDeps.MOCK_K)
    // Truth
    testImplementation(TestDeps.TRUTH)
}

fun DependencyHandler.addInstrumentationTestDependencies() {

    // AndroidX Test - Instrumented testing
    androidTestImplementation(TestDeps.ANDROIDX_JUNIT)
    androidTestImplementation(TestDeps.ANDROIDX_CORE_TESTING)

    // Espresso
    androidTestImplementation(TestDeps.ANDROIDX_ESPRESSO)

    // Testing Navigation
    androidTestImplementation(TestDeps.NAVIGATION_TEST)

    // Coroutines Test
    androidTestImplementation(TestDeps.COROUTINES_TEST)

    // MockWebServer
    androidTestImplementation(TestDeps.MOCK_WEB_SERVER)
    // Gson
    androidTestImplementation(TestDeps.GSON)

    // MockK
    androidTestImplementation(TestDeps.MOCK_K)
    // Truth
    androidTestImplementation(TestDeps.TRUTH)
}

/*
 * These extensions mimic the extensions that are generated on the fly by Gradle.
 * They are used here to provide above dependency syntax that mimics Gradle Kotlin DSL
 * syntax in module\build.gradle.kts files.
 */
@Suppress("detekt.UnusedPrivateMember")
private fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

@Suppress("detekt.UnusedPrivateMember")
private fun DependencyHandler.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)

@Suppress("detekt.UnusedPrivateMember")
private fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

private fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

private fun DependencyHandler.debugImplementation(dependencyNotation: Any): Dependency? =
    add("debugImplementation", dependencyNotation)

private fun DependencyHandler.testRuntimeOnly(dependencyNotation: Any): Dependency? =
    add("testRuntimeOnly", dependencyNotation)

private fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

private fun DependencyHandler.project(
    path: String,
    configuration: String? = null
): ProjectDependency {
    val notation = if (configuration != null) {
        mapOf("path" to path, "configuration" to configuration)
    } else {
        mapOf("path" to path)
    }

    return uncheckedCast(project(notation))
}

@Suppress("unchecked_cast", "nothing_to_inline", "detekt.UnsafeCast")
private inline fun <T> uncheckedCast(obj: Any?): T = obj as T