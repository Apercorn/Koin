package dev.apercorn.koin

class JVMPlatform : Platform {
	override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()