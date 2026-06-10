package dev.apercorn.koin.core.di

import dev.apercorn.koin.core.data.repository.*
import org.koin.dsl.module

val repositoryModule = module {
	single<AccountRepository> { AccountRepositoryImpl(get(), get()) }
	single<TransactionRepository> { TransactionRepositoryImpl(get()) }
	single<BudgetRepository> { BudgetRepositoryImpl(get()) }
	single<CategoryRepository> { CategoryRepositoryImpl(get()) }
}