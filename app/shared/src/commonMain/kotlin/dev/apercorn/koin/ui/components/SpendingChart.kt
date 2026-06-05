package dev.apercorn.koin.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.apercorn.koin.ui.theme.KoinColors

// Placeholder chart component using Material3 components
// Vico chart integration will replace this in a later iteration
@Composable
fun SpendingChart(
	data: List<ChartBar>,
	modifier: Modifier = Modifier
) {
	if (data.isEmpty()) {
		Card(modifier = modifier.fillMaxWidth()) {
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.height(160.dp),
				contentAlignment = androidx.compose.ui.Alignment.Center
			) {
				Text(
					text = "No spending data",
					color = MaterialTheme.colorScheme.onSurfaceVariant
				)
			}
		}
		return
	}

	val maxValue = data.maxOfOrNull { it.value }?.takeIf { it > 0f } ?: 1f

	Card(modifier = modifier.fillMaxWidth()) {
		Column(
			modifier = Modifier.padding(16.dp)
		) {
			Text(
				text = "Spending by Category",
				style = MaterialTheme.typography.titleSmall,
				color = MaterialTheme.colorScheme.onSurfaceVariant
			)
			Spacer(modifier = Modifier.height(12.dp))

			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceEvenly
			) {
				data.take(5).forEach { bar ->
					Column(
						modifier = Modifier.width(32.dp),
						horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
					) {
						Box(
							modifier = Modifier
								.width(24.dp)
								.height((bar.value / maxValue * 100).dp.coerceAtLeast(8.dp)),
							contentAlignment = androidx.compose.ui.Alignment.Center
						) {
							Surface(
								modifier = Modifier
									.fillMaxWidth()
									.fillMaxHeight(),
								shape = MaterialTheme.shapes.small,
								color = KoinColors.Green.copy(
									alpha = 0.3f + (bar.value / maxValue * 0.7f)
								)
							) {}
						}
						Spacer(modifier = Modifier.height(4.dp))
						Text(
							text = bar.label,
							style = MaterialTheme.typography.bodySmall,
							color = MaterialTheme.colorScheme.onSurfaceVariant
						)
					}
				}
			}
		}
	}
}

data class ChartBar(
	val label: String,
	val value: Float
)