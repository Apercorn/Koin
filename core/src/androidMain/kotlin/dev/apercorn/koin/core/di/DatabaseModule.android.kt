package dev.apercorn.koin.core.di

import android.content.Context
import dev.apercorn.koin.core.data.database.*
import kotlinx.coroutines.*

lateinit var appContext: Context

actual fun createDatabase(): AppDatabase {
	val db = createRoomDatabase(appContext)
	// Seed categories on first launch
	CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
		seedDefaultCategories(db)
	}
	return db
}