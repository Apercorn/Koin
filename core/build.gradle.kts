import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	alias(libs.plugins.kotlinMultiplatform)
	alias(libs.plugins.androidMultiplatformLibrary)
	alias(libs.plugins.ksp)
}

kotlin {
	iosArm64()
	iosSimulatorArm64()

	jvm()

	androidLibrary {
		namespace = "dev.apercorn.koin.core"
		compileSdk = libs.versions.android.compileSdk.get().toInt()
		minSdk = libs.versions.android.minSdk.get().toInt()

		compilerOptions {
			jvmTarget = JvmTarget.JVM_11
		}
		androidResources {
			enable = true
		}
		withHostTest {
			isIncludeAndroidResources = true
		}
	}

	sourceSets {
		androidMain.dependencies {
			implementation(libs.room.ktx)
		}
		commonMain.dependencies {
			// Room
			implementation(libs.room.runtime)

			// Koin
			implementation(libs.koin.core)

			// Kotlinx
			implementation(libs.kotlinx.coroutines.core)
			implementation(libs.kotlinx.serialization.json)
			implementation(libs.kotlinx.datetime)

			// DataStore
			implementation(libs.datastore.preferences.core)

			// UUID
			implementation(libs.uuid)

			// Ktor client (for network module)
			implementation(libs.ktor.clientCore)
			implementation(libs.ktor.clientContentNegotiation)
			implementation(libs.ktor.serializationKotlinxJson)
		}
		commonTest.dependencies {
			implementation(libs.kotlin.test)
		}
	}
}

dependencies {
	add("kspCommonMainMetadata", libs.room.compiler)
	add("kspAndroid", libs.room.compiler)
	add("kspIosArm64", libs.room.compiler)
	add("kspIosSimulatorArm64", libs.room.compiler)
	add("kspJvm", libs.room.compiler)
}