package dev.apercorn.koin.ui.screens.transactions.entry.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.TablerIcons
import compose.icons.tablericons.*

@Composable
fun AmountDisplay(
	rawExpression: String,
	onAdjustClick: () -> Unit,
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier
			.fillMaxWidth()
			.padding(horizontal = 8.dp),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.End
	) {
		// amount text
		Text(
			text = rawExpression,
			style = MaterialTheme.typography.displayLarge.copy(
				fontSize = 36.sp,
				fontWeight = FontWeight.Bold
			),
			color = MaterialTheme.colorScheme.onSurface,
			modifier = Modifier.weight(1f)
		)

		Spacer(modifier = Modifier.width(12.dp))

		// adjustment button
		IconButton(
			onClick = onAdjustClick,
			modifier = Modifier.size(44.dp)
		) {
			Surface(
				shape = CircleShape,
				color = MaterialTheme.colorScheme.surface,
				modifier = Modifier.size(40.dp)
			) {
				Box(contentAlignment = Alignment.Center) {
					Icon(
						imageVector = TablerIcons.CurrencyDollar,
						contentDescription = "Adjustments",
						tint = MaterialTheme.colorScheme.onSurfaceVariant,
						modifier = Modifier.size(22.dp)
					)
				}
			}
		}
	}
}
