plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.jetbrains.kotlin.android)
  id("androidx.navigation.safeargs.kotlin")
  id("kotlin-kapt")
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

  implementation(libs.material)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
}