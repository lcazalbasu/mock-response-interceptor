plugins {
    alias(libs.plugins.defined.android.library)
}

android {
    namespace = "com.lcazalbasu.mockresponseinterceptor"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

    implementation(libs.retrofit)
    implementation(libs.okhttp3.okhttp)
}