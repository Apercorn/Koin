package dev.apercorn.koin.di

import dev.apercorn.koin.ui.screens.accounts.AccountsViewModel
import dev.apercorn.koin.ui.screens.budgets.BudgetsViewModel
import dev.apercorn.koin.ui.screens.overview.OverviewViewModel
import dev.apercorn.koin.ui.screens.transactions.TransactionViewModel
import org.koin.dsl.module

val screenModelModule = module {
	factory { OverviewViewModel(get(), get(), get()) }
	factory { TransactionViewModel(get(), get(), get()) }
	factory { AccountsViewModel(get(), get()) }
	factory { BudgetsViewModel(get()) }
}
