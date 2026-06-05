package dev.apercorn.koin.core.di

import dev.apercorn.koin.core.domain.usecase.AddTransactionUseCase
import dev.apercorn.koin.core.domain.usecase.CheckBudgetStatusUseCase
import dev.apercorn.koin.core.domain.usecase.DeleteAccountUseCase
import dev.apercorn.koin.core.domain.usecase.GetAccountsUseCase
import dev.apercorn.koin.core.domain.usecase.GetSpendingByPeriodUseCase
import dev.apercorn.koin.core.domain.usecase.ProcessRecurringUseCase
import org.koin.dsl.module

val useCaseModule = module {
	factory { GetAccountsUseCase(get(), get()) }
	factory { AddTransactionUseCase(get(), get()) }
	factory { GetSpendingByPeriodUseCase(get()) }
	factory { CheckBudgetStatusUseCase(get(), get()) }
	factory { ProcessRecurringUseCase(get(), get()) }
	factory { DeleteAccountUseCase(get()) }
}