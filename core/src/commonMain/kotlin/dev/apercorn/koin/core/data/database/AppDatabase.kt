package dev.apercorn.koin.core.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import dev.apercorn.koin.core.data.database.converters.Converters
import dev.apercorn.koin.core.data.database.daos.AccountDao
import dev.apercorn.koin.core.data.database.daos.BudgetDao
import dev.apercorn.koin.core.data.database.daos.CategoryDao
import dev.apercorn.koin.core.data.database.daos.RecurringDao
import dev.apercorn.koin.core.data.database.daos.TransactionDao
import dev.apercorn.koin.core.data.database.entities.AccountEntity
import dev.apercorn.koin.core.data.database.entities.BudgetEntity
import dev.apercorn.koin.core.data.database.entities.CategoryEntity
import dev.apercorn.koin.core.data.database.entities.CounterpartyEntity
import dev.apercorn.koin.core.data.database.entities.GoalEntity
import dev.apercorn.koin.core.data.database.entities.RecurringEntity
import dev.apercorn.koin.core.data.database.entities.TransactionEntity

@Database(
	entities = [
		AccountEntity::class,
		TransactionEntity::class,
		CategoryEntity::class,
		BudgetEntity::class,
		GoalEntity::class,
		RecurringEntity::class,
		CounterpartyEntity::class
	],
	version = 2,
	exportSchema = false
)
@TypeConverters(Converters::class)
@ConstructedBy(AppDatabaseCtor::class)
abstract class AppDatabase : RoomDatabase() {
	abstract fun accountDao(): AccountDao
	abstract fun transactionDao(): TransactionDao
	abstract fun categoryDao(): CategoryDao
	abstract fun budgetDao(): BudgetDao
	abstract fun recurringDao(): RecurringDao
}

expect object AppDatabaseCtor : RoomDatabaseConstructor<AppDatabase>