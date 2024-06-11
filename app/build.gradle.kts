plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.example.hl_entrega"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.hl_entrega"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
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
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    implementation(libs.play.services.maps)
    implementation(libs.androidx.camera.view)
    implementation(libs.androidx.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(kotlin("stdlib"))
    implementation("mysql:mysql-connector-java:8.0.28")
    testImplementation("junit", "junit", "4.12")
    implementation ("androidx.sqlite:sqlite:2.1.0")

    // CameraX core library using the camera2 implementation
    val cameraxVersion = "1.3.3"
    // The following line is optional, as the core library is included indirectly by camera-camera2
    implementation("androidx.camera:camera-core:${cameraxVersion}")
    implementation("androidx.camera:camera-camera2:${cameraxVersion}")
    // If you want to additionally use the CameraX Lifecycle library
    implementation("androidx.camera:camera-lifecycle:${cameraxVersion}")
    // If you want to additionally use the CameraX VideoCapture library
    //    implementation "androidx.camera:camera-video:${camerax_version}"
    // If you want to additionally use the CameraX View class
    implementation("androidx.camera:camera-view:${cameraxVersion}")
    // If you want to additionally add CameraX ML Kit Vision Integration
    //       implementation "androidx.camera:camera-mlkit-vision:${camerax_version}"
    // If you want to additionally use the CameraX Extensions library
    implementation("androidx.camera:camera-extensions:${cameraxVersion}")


    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    implementation ("androidx.databinding:viewbinding:7.0.3")
    implementation ("androidx.appcompat:appcompat:1.3.1")



}