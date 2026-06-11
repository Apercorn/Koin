package dev.apercorn.koin.ui.screens.transactions.entry.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.apercorn.koin.ui.screens.transactions.entry.PartyUiModel

/**
 * Single pill/card representing the FROM or TO side of a transaction — account or category.
 */
@Composable
fun TransactionCategoryButton(
	party: PartyUiModel,
	onClick: () -> Unit,
	modifier: Modifier = Modifier
) {
	Card(
		onClick = onClick,
		shape = RoundedCornerShape(16.dp),
		colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
		modifier = modifier
	) {
		Column(
			modifier = Modifier
				.padding(horizontal = 16.dp, vertical = 12.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Text(
				text = party.roleTag,
				style = MaterialTheme.typography.labelSmall,
				fontWeight = FontWeight.SemiBold,
				color = MaterialTheme.colorScheme.onSurfaceVariant
			)
			Spacer(modifier = Modifier.height(4.dp))
			Row(
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.spacedBy(8.dp)
			) {
				Icon(
					imageVector = party.icon,
					contentDescription = party.label,
					tint = MaterialTheme.colorScheme.primary,
					modifier = Modifier.size(20.dp)
				)
				Text(
					text = party.label,
					style = MaterialTheme.typography.labelLarge,
					fontWeight = FontWeight.Medium,
					color = MaterialTheme.colorScheme.onSurface
				)
			}
		}
	}
}
