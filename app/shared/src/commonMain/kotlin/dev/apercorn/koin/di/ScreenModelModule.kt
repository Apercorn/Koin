package dev.apercorn.koin.di

import dev.apercorn.koin.ui.accounts.AccountsViewModel
import dev.apercorn.koin.ui.budgets.BudgetsViewModel
import dev.apercorn.koin.ui.overview.OverviewViewModel
import dev.apercorn.koin.ui.transactions.TransactionViewModel
import org.koin.dsl.module

val screenModelModule = module {
	factory { OverviewViewModel(get(), get(), get()) }
	factory { TransactionViewModel(get(), get(), get()) }
	factory { AccountsViewModel(get(), get()) }
	factory { BudgetsViewModel(get()) }
}
