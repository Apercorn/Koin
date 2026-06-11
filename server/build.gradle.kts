import org.gradle.kotlin.dsl.libs

plugins {
	alias(libs.plugins.kotlinJvm)
	alias(libs.plugins.ktor)
}

group = "dev.apercorn.koin"
version = "1.0.0"
application {
	mainClass = "dev.apercorn.koin.ApplicationKt"
}

dependencies {
	implementation(projects.core)
	implementation(libs.logback)
	implementation(libs.ktor.serverCore)
	implementation(libs.ktor.serverNetty)
	testImplementation(libs.ktor.serverTestHost)
	testImplementation(libs.kotlin.testJunit)
}