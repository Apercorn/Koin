package dev.apercorn.koin.core.di

import dev.apercorn.koin.core.data.database.AppDatabase
import dev.apercorn.koin.core.data.database.createRoomDatabase
import dev.apercorn.koin.core.data.database.seedDefaultCategories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

actual fun createDatabase(): AppDatabase {
	val db = createRoomDatabase()
	CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
		seedDefaultCategories(db)
	}
	return db
}