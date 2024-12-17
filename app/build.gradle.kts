plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.jetbrains.kotlin.android)
  id("androidx.navigation.safeargs.kotlin")
  id("kotlin-kapt")
  id("com.google.dagger.hilt.android")
}

android {
  namespace = "edu.programmingclasses2024winter"
  compileSdk = 34

  defaultConfig {
    applicationId = "edu.programmingclasses2024winter"
    minSdk = 30
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildFeatures {
    dataBinding = true
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  val nav_version = "2.8.3"

  implementation("androidx.navigation:navigation-compose:$nav_version")
  implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
  implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
  implementation("com.squareup.okhttp3:okhttp:4.12.0")
  implementation("com.squareup.retrofit2:retrofit:2.11.0")
  implementation("com.squareup.retrofit2:converter-gson:2.11.0")
  implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
  implementation("androidx.datastore:datastore-preferences:1.1.1")
  val room_version = "2.6.1"

  implementation("androidx.room:room-runtime:$room_version")
  implementation("androidx.room:room-ktx:$room_version")
  annotationProcessor("androidx.room:room-compiler:$room_version")

  // To use Kotlin annotation processing tool (kapt)
  kapt("androidx.room:room-compiler:$room_version")

  implementation("com.google.dagger:hilt-android:2.50")
  kapt("com.google.dagger:hilt-android-compiler:2.50")
  
  implementation(libs.material)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
}