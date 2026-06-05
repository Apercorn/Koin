package dev.apercorn.koin.ui.transactions

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

object TransactionScreen : Screen {

	@Composable
	override fun Content() {
		val viewModel = getScreenModel<TransactionViewModel>()
		val state by viewModel.state.collectAsState()

		Column(modifier = Modifier.fillMaxSize()) {
			// Header
			Text(
				text = "Transactions",
				style = MaterialTheme.typography.headlineMedium,
				fontWeight = FontWeight.Bold,
				modifier = Modifier.padding(16.dp)
			)

			if (state.isLoading) {
				Box(
					modifier = Modifier.fillMaxSize(),
					contentAlignment = Alignment.Center
				) {
					CircularProgressIndicator()
				}
			} else if (state.transactions.isEmpty()) {
				Box(
					modifier = Modifier.fillMaxSize(),
					contentAlignment = Alignment.Center
				) {
					Text(
						text = "No transactions yet",
						color = MaterialTheme.colorScheme.onSurfaceVariant
					)
				}
			} else {
				LazyColumn(
					contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
					verticalArrangement = Arrangement.spacedBy(8.dp)
				) {
					items(state.transactions) { transaction ->
						TransactionListItem(transaction)
					}
				}
			}
		}
	}
}

@Composable
private fun TransactionListItem(transaction: dev.apercorn.koin.core.domain.model.Transaction) {
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
			Column(modifier = Modifier.weight(1f)) {
				Text(
					text = transaction.note ?: transaction.type.name,
					style = MaterialTheme.typography.bodyMedium,
					fontWeight = FontWeight.Medium
				)
				Row(
					horizontalArrangement = Arrangement.spacedBy(8.dp)
				) {
					Text(
						text = DateUtils.formatShort(transaction.date),
						style = MaterialTheme.typography.bodySmall,
						color = MaterialTheme.colorScheme.onSurfaceVariant
					)
					Text(
						text = transaction.type.name,
						style = MaterialTheme.typography.bodySmall,
						color = MaterialTheme.colorScheme.primary
					)
				}
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