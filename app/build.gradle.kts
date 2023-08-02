plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization")
}

android {
    namespace = "moe.fuqiuluo.shamrock"
    compileSdk = 33

    defaultConfig {
        applicationId = "moe.fuqiuluo.shamrock"
        minSdk = 24
        targetSdk = 33
        versionCode = 2
        versionName = "1.0.0"

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes +=  "/META-INF/{AL2.0,LGPL2.1}"
            excludes +=  "/META-INF/*"
            excludes +=  "/META-INF/NOTICE.txt"
            excludes +=  "/META-INF/DEPENDENCIES.txt"
            excludes +=  "/META-INF/NOTICE"
            excludes +=  "/META-INF/LICENSE"
            excludes +=  "/META-INF/DEPENDENCIES"
            excludes +=  "/META-INF/notice.txt"
            excludes +=  "/META-INF/dependencies.txt"
            excludes +=  "/META-INF/LGPL2.1"
            excludes +=  "/META-INF/ASL2.0"
            excludes +=  "/META-INF/INDEX.LIST"
            excludes +=  "/META-INF/io.netty.versions.properties"
            excludes +=  "/META-INF/INDEX.LIST"
            excludes +=  "/META-INF/LICENSE.txt"
            excludes +=  "/META-INF/license.txt"
            excludes +=  "/META-INF/*.kotlin_module"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.06.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    //noinspection GradleDynamicVersion
    implementation("com.google.accompanist:accompanist-pager:0.31.5+")
    //noinspection GradleDynamicVersion
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.31.5+")
    //noinspection GradleDynamicVersion useless
    // implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0+")
    implementation("io.coil-kt:coil:2.4.0")
    implementation("io.coil-kt:coil-compose:2.4.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    implementation(project(":xposed"))

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.06.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}