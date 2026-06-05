package dev.apercorn.koin.core.data.database

import androidx.room.Room
import dev.apercorn.koin.core.domain.SeedCategories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

fun createRoomDatabase(): AppDatabase {
	val dbPath = System.getProperty("user.home") + "/Library/koin.db"
	return Room.databaseBuilder<AppDatabase>(
		name = dbPath
	)
		.setQueryCoroutineContext(Dispatchers.IO)
		.fallbackToDestructiveMigration()
		.build()
}

suspend fun seedDefaultCategories(database: AppDatabase) {
	val dao = database.categoryDao()
	if (dao.count() == 0) {
		dao.upsertAll(SeedCategories.defaults)
	}
}