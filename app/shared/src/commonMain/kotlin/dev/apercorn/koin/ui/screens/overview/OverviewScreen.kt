package dev.apercorn.koin.ui.screens.overview

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import dev.apercorn.koin.core.util.CurrencyFormatter
import dev.apercorn.koin.core.util.DateUtils
import dev.apercorn.koin.ui.theme.KoinColors

object OverviewScreen : Screen {

	@Composable
	override fun Content() {
		val viewModel = getScreenModel<OverviewViewModel>()
		val state by viewModel.state.collectAsState()

		LazyColumn(
			modifier = Modifier.fillMaxSize(),
			contentPadding = PaddingValues(16.dp),
			verticalArrangement = Arrangement.spacedBy(16.dp)
		) {
			// Balance Card
			item {
				BalanceCard(state.totalBalance, state.baseCurrency)
			}

			// Monthly Summary
			item {
				MonthlySummaryCard(
					income = state.monthlyIncome,
					expenses = state.monthlyExpenses,
					currency = state.baseCurrency
				)
			}

			// Recent Transactions
			item {
				Text(
					text = "Recent Transactions",
					style = MaterialTheme.typography.titleMedium,
					fontWeight = FontWeight.Bold
				)
			}

			if (state.recentTransactions.isEmpty()) {
				item {
					Text(
						text = "No transactions yet. Tap + to add one.",
						color = MaterialTheme.colorScheme.onSurfaceVariant,
						modifier = Modifier.padding(vertical = 16.dp)
					)
				}
			} else {
				items(state.recentTransactions) { transaction ->
					TransactionRow(transaction)
				}
			}
		}
	}
}

@Composable
private fun BalanceCard(balance: Long, currency: String) {
	Card(
		modifier = Modifier.fillMaxWidth(),
		colors = CardDefaults.cardColors(
			containerColor = KoinColors.GreenContainer
		)
	) {
		Column(
			modifier = Modifier.padding(24.dp)
		) {
			Text(
				text = "Total Balance",
				color = KoinColors.DarkOnSurface.copy(alpha = 0.8f),
				style = MaterialTheme.typography.bodyMedium
			)
			Spacer(modifier = Modifier.height(8.dp))
			Text(
				text = CurrencyFormatter.format(balance, currency),
				color = KoinColors.DarkOnSurface,
				style = MaterialTheme.typography.headlineLarge,
				fontWeight = FontWeight.Bold
			)
		}
	}
}

@Composable
private fun MonthlySummaryCard(income: Long, expenses: Long, currency: String) {
	Card(
		modifier = Modifier.fillMaxWidth()
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(16.dp),
			horizontalArrangement = Arrangement.SpaceEvenly
		) {
			Column(horizontalAlignment = Alignment.CenterHorizontally) {
				Text(
					text = "Income",
					style = MaterialTheme.typography.bodySmall,
					color = MaterialTheme.colorScheme.onSurfaceVariant
				)
				Spacer(modifier = Modifier.height(4.dp))
				Text(
					text = CurrencyFormatter.format(income, currency),
					color = KoinColors.Income,
					style = MaterialTheme.typography.titleMedium,
					fontWeight = FontWeight.SemiBold
				)
			}
			Column(horizontalAlignment = Alignment.CenterHorizontally) {
				Text(
					text = "Expenses",
					style = MaterialTheme.typography.bodySmall,
					color = MaterialTheme.colorScheme.onSurfaceVariant
				)
				Spacer(modifier = Modifier.height(4.dp))
				Text(
					text = CurrencyFormatter.format(expenses, currency),
					color = KoinColors.Expense,
					style = MaterialTheme.typography.titleMedium,
					fontWeight = FontWeight.SemiBold
				)
			}
		}
	}
}

@Composable
private fun TransactionRow(transaction: dev.apercorn.koin.core.domain.model.Transaction) {
	Card(
		modifier = Modifier.fillMaxWidth()
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(12.dp),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			Column {
				Text(
					text = transaction.note ?: transaction.type.name,
					style = MaterialTheme.typography.bodyMedium
				)
				Text(
					text = DateUtils.formatShort(transaction.date),
					style = MaterialTheme.typography.bodySmall,
					color = MaterialTheme.colorScheme.onSurfaceVariant
				)
			}
			Text(
				text = "${if (transaction.type == dev.apercorn.koin.core.domain.model.TransactionType.EXPENSE) "-" else "+"}${
					CurrencyFormatter.format(
						transaction.amount,
						transaction.currency
					)
				}",
				color = if (transaction.type == dev.apercorn.koin.core.domain.model.TransactionType.EXPENSE) KoinColors.Expense else KoinColors.Income,
				style = MaterialTheme.typography.titleSmall,
				fontWeight = FontWeight.SemiBold
			)
		}
	}
}