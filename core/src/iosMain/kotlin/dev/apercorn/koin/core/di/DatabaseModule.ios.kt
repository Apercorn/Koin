package dev.apercorn.koin.core.di

import dev.apercorn.koin.core.data.database.*
import kotlinx.coroutines.*

actual fun createDatabase(): AppDatabase {
	val db = createRoomDatabase()
	CoroutineScope(SupervisorJob() + Dispatchers.Default).launch {
		seedDefaultCategories(db)
	}
	return db
}
