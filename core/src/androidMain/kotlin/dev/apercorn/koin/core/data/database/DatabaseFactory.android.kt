package dev.apercorn.koin.core.data.database

import android.content.Context
import androidx.room.Room

fun createRoomDatabase(context: Context): AppDatabase {
	return Room.databaseBuilder<AppDatabase>(
		context = context,
		name = "koin.db"
	)
		.fallbackToDestructiveMigration(true)
		.build()
}
