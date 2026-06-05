package dev.apercorn.koin.core.data.database

import android.content.Context
import androidx.room.Room
import dev.apercorn.koin.core.domain.SeedCategories

fun createRoomDatabase(context: Context): AppDatabase {
	return Room.databaseBuilder<AppDatabase>(
		context = context,
		name = "koin.db"
	)
		.fallbackToDestructiveMigration()
		.build()
}

suspend fun seedDefaultCategories(database: AppDatabase) {
	val dao = database.categoryDao()
	if (dao.count() == 0) {
		dao.upsertAll(SeedCategories.defaults)
	}
}