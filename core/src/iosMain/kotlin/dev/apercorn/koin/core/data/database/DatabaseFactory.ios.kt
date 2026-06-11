package dev.apercorn.koin.core.data.database

import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import platform.Foundation.NSHomeDirectory

fun createRoomDatabase(): AppDatabase {
	val dbPath = NSHomeDirectory() + "/Documents/koin.db"
	return Room.databaseBuilder<AppDatabase>(
		name = dbPath
	)
		.setQueryCoroutineContext(Dispatchers.Default)
		.fallbackToDestructiveMigration(true)
		.build()
}
