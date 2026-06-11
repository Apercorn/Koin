package dev.apercorn.koin.core.data.database

import androidx.room.Room
import java.io.File

fun createRoomDatabase(): AppDatabase {
	val dbFile = File(System.getProperty("user.home"), ".koin/koin.db")
	if (!dbFile.parentFile.exists()) dbFile.parentFile.mkdirs()

	return Room.databaseBuilder<AppDatabase>(
		name = dbFile.absolutePath
	)
		.fallbackToDestructiveMigration(true)
		.build()
}
