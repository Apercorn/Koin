package dev.apercorn.koin.core.di

import dev.apercorn.koin.core.data.repository.AccountRepository
import dev.apercorn.koin.core.data.repository.AccountRepositoryImpl
import dev.apercorn.koin.core.data.repository.BudgetRepository
import dev.apercorn.koin.core.data.repository.BudgetRepositoryImpl
import dev.apercorn.koin.core.data.repository.CategoryRepository
import dev.apercorn.koin.core.data.repository.CategoryRepositoryImpl
import dev.apercorn.koin.core.data.repository.TransactionRepository
import dev.apercorn.koin.core.data.repository.TransactionRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
	single<AccountRepository> { AccountRepositoryImpl(get(), get()) }
	single<TransactionRepository> { TransactionRepositoryImpl(get()) }
	single<BudgetRepository> { BudgetRepositoryImpl(get()) }
	single<CategoryRepository> { CategoryRepositoryImpl(get()) }
}