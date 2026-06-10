package dev.apercorn.koin.core.di

import dev.apercorn.koin.core.data.database.AppDatabase
import dev.apercorn.koin.core.data.database.daos.*
import org.koin.dsl.module

val databaseModule = module {
	single<AppDatabase> { createDatabase() }
	single<AccountDao> { get<AppDatabase>().accountDao() }
	single<TransactionDao> { get<AppDatabase>().transactionDao() }
	single<CategoryDao> { get<AppDatabase>().categoryDao() }
	single<BudgetDao> { get<AppDatabase>().budgetDao() }
	single<RecurringDao> { get<AppDatabase>().recurringDao() }
}

expect fun createDatabase(): AppDatabase