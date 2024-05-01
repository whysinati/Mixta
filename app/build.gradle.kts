import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.apolloGraphQl)
    alias(libs.plugins.googleFigmaRelay)
    alias(libs.plugins.ktLint)
}

// Creates a variable called keystorePropertiesFile, and initializes it to the
// keystore.properties file.
val keystorePropertiesFile = rootProject.file("keystore.properties")

// Initializes a new Properties() object called keystoreProperties.
val keystoreProperties = Properties()

// Loads the keystore.properties file into the keystoreProperties object.
keystoreProperties.load(FileInputStream(keystorePropertiesFile))
// keystoreProperties.getProperty("token")
android {
    namespace = "com.example.mixta"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mixta"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        defaultConfig {
            buildConfigField("String", "TOKEN", keystoreProperties.getProperty("token"))
            resValue("string", "token", keystoreProperties.getProperty("token"))
            //            buildConfigField("String", "BUILD_TIME", "\"${minutesSinceEpoch}\"")
//            resValue("string", "build_time", "${minutesSinceEpoch}")
        }
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
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    // here, the dependency notation is a dot replacing the dashes of the variable name given from the toml file
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.apollo.graphql.runtime)
    implementation(libs.coil)
    implementation(libs.compose.viewmodel)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

apollo {
    service("contentful") {
        packageName.set("com.example.mixta")
        generateOptionalOperationVariables.set(false)
    }
}