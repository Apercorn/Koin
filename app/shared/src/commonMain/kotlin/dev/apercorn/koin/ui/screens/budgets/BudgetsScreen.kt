package dev.apercorn.koin.ui.screens.budgets

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
import dev.apercorn.koin.ui.theme.KoinColors

object BudgetsScreen : Screen {

	@Composable
	override fun Content() {
		val viewModel = getScreenModel<BudgetsViewModel>()
		val state by viewModel.state.collectAsState()

		Column(modifier = Modifier.fillMaxSize()) {
			Text(
				text = "Budgets",
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
			} else if (state.budgets.isEmpty()) {
				Box(
					modifier = Modifier.fillMaxSize(),
					contentAlignment = Alignment.Center
				) {
					Text(
						text = "No budgets set. Add budgets to track spending limits.",
						color = MaterialTheme.colorScheme.onSurfaceVariant
					)
				}
			} else {
				LazyColumn(
					contentPadding = PaddingValues(16.dp),
					verticalArrangement = Arrangement.spacedBy(12.dp)
				) {
					items(state.budgets) { budgetWithSpending ->
						BudgetCard(
							categoryName = budgetWithSpending.budget.categoryId,
							monthlyCap = budgetWithSpending.budget.monthlyCap,
							currentSpending = budgetWithSpending.currentSpending,
							percentage = budgetWithSpending.percentage,
							currency = budgetWithSpending.budget.currency
						)
					}
				}
			}
		}
	}
}

@Composable
private fun BudgetCard(
	categoryName: String,
	monthlyCap: Long,
	currentSpending: Long,
	percentage: Float,
	currency: String
) {
	Card(modifier = Modifier.fillMaxWidth()) {
		Column(
			modifier = Modifier.padding(16.dp)
		) {
			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				Text(
					text = categoryName,
					style = MaterialTheme.typography.titleMedium,
					fontWeight = FontWeight.Medium
				)
				Text(
					text = CurrencyFormatter.format(currentSpending, currency),
					style = MaterialTheme.typography.titleSmall,
					fontWeight = FontWeight.SemiBold
				)
			}
			Spacer(modifier = Modifier.height(8.dp))
			LinearProgressIndicator(
				progress = { percentage },
				modifier = Modifier
					.fillMaxWidth()
					.height(8.dp),
				color = if (percentage < 0.75f) KoinColors.Income
				else if (percentage < 1f) KoinColors.Warning
				else KoinColors.Expense,
				trackColor = MaterialTheme.colorScheme.surfaceVariant
			)
			Spacer(modifier = Modifier.height(4.dp))
			Text(
				text = "${(percentage * 100).toInt()}% of ${CurrencyFormatter.format(monthlyCap, currency)}",
				style = MaterialTheme.typography.bodySmall,
				color = MaterialTheme.colorScheme.onSurfaceVariant
			)
		}
	}
}
