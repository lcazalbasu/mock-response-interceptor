import com.lcazalbasu.apps.buildlogic.AppVersions

plugins {
    alias(libs.plugins.defined.android.library)
    id("maven-publish")
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
                "proguard-rules.pro",
            )
        }
    }
}

dependencies {

    implementation(libs.retrofit)
    implementation(libs.okhttp3.okhttp)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components.findByName("release"))
                groupId = "com.lcazalbasu"
                artifactId = "mockresponseinterceptor"
                version = AppVersions.VERSION_NAME
            }
        }
    }
}