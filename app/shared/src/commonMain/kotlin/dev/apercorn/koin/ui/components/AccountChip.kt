package dev.apercorn.koin.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.apercorn.koin.ui.theme.KoinColors

@Composable
fun AccountChip(
	name: String,
	type: String,
	balance: Long,
	currency: String,
	modifier: Modifier = Modifier,
	isSelected: Boolean = false
) {
	val displayBalance = dev.apercorn.koin.core.util.CurrencyFormatter.format(balance, currency)

	Card(
		modifier = modifier,
		colors = if (isSelected) CardDefaults.cardColors(
			containerColor = MaterialTheme.colorScheme.primaryContainer
		) else CardDefaults.cardColors()
	) {
		Row(
			modifier = Modifier.padding(12.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			Column(modifier = Modifier.weight(1f)) {
				Text(
					text = name,
					style = MaterialTheme.typography.bodyMedium,
					fontWeight = FontWeight.Medium
				)
				Text(
					text = type,
					style = MaterialTheme.typography.bodySmall,
					color = MaterialTheme.colorScheme.onSurfaceVariant
				)
			}
			Text(
				text = displayBalance,
				style = MaterialTheme.typography.bodyMedium,
				fontWeight = FontWeight.SemiBold,
				color = if (balance >= 0) KoinColors.Income else KoinColors.Expense
			)
		}
	}
}