package dev.apercorn.koin.ui.screens.transactions.entry.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import compose.icons.TablerIcons
import compose.icons.tablericons.*
import dev.apercorn.koin.ui.screens.transactions.entry.PartyUiModel

/**
 * Horizontal row showing FROM → TO with a directional arrow in between.
 */
@Composable
fun TransactionPartyRow(
	fromParty: PartyUiModel,
	toParty: PartyUiModel,
	onFromClick: () -> Unit,
	onToClick: () -> Unit,
	onArrowClick: () -> Unit,
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier.fillMaxWidth(),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.spacedBy(8.dp)
	) {
		TransactionCategoryButton(
			party = fromParty,
			onClick = onFromClick,
			modifier = Modifier.weight(1f)
		)

		// directional arrow
		Icon(
			imageVector = TablerIcons.ArrowRight,
			contentDescription = "Direction",
			tint = MaterialTheme.colorScheme.onSurfaceVariant,
			modifier = Modifier.size(24.dp).padding(horizontal = 4.dp)
		)

		TransactionCategoryButton(
			party = toParty,
			onClick = onToClick,
			modifier = Modifier.weight(1f)
		)
	}
}
