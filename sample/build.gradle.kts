import com.lcazalbasu.apps.buildlogic.AppVersions

plugins {
    alias(libs.plugins.defined.android.application)
    alias(libs.plugins.defined.android.application.compose)
    alias(libs.plugins.defined.android.hilt)
}

android {
    namespace = "com.lcazalbasu.mockresponseinterceptor"

    defaultConfig {
        applicationId = "com.lcazalbasu.mockresponseinterceptor"
        versionCode = AppVersions.VERSION_CODE
        versionName = AppVersions.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":lib"))

    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp3.okhttp)
    implementation(libs.okhttp3.logging)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation.hilt)

    implementation(libs.bundles.coil)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
}