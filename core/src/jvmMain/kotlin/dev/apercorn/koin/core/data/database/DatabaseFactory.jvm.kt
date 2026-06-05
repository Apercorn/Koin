package dev.apercorn.koin.core.data.database

import androidx.room.Room
import dev.apercorn.koin.core.domain.SeedCategories
import kotlinx.coroutines.Dispatchers

fun createRoomDatabase(): AppDatabase {
	return Room.databaseBuilder<AppDatabase>(
		name = "koin.db"
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