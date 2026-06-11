package dev.apercorn.koin.core.di

import dev.apercorn.koin.core.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
	factory { GetAccountsUseCase(get(), get()) }
	factory { AddTransactionUseCase(get(), get()) }
	factory { GetSpendingByPeriodUseCase(get()) }
	factory { CheckBudgetStatusUseCase(get(), get()) }
	factory { ProcessRecurringUseCase(get(), get()) }
	factory { DeleteAccountUseCase(get()) }
}