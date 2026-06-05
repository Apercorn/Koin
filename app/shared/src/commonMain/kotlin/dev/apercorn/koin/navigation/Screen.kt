package dev.apercorn.koin.navigation

sealed class AppScreen {
	data object Overview : AppScreen()
	data object Transactions : AppScreen()
	data object Accounts : AppScreen()
	data object Budgets : AppScreen()
	data object Settings : AppScreen()
	data object NewTransaction : AppScreen()
}