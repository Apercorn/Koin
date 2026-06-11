package dev.apercorn.koin.ui.screens.accounts.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.apercorn.koin.ui.components.CircularIcon
import dev.apercorn.koin.ui.util.IconProvider

@Composable
fun IconPickerGrid(
	selectedIconName: String?,
	onIconSelected: (String) -> Unit,
	modifier: Modifier = Modifier
) {
	LazyVerticalGrid(
		columns = GridCells.Fixed(5),
		modifier = modifier.fillMaxWidth(),
		horizontalArrangement = Arrangement.spacedBy(12.dp),
		verticalArrangement = Arrangement.spacedBy(12.dp)
	) {
		items(IconProvider.iconPalette.entries.toList()) { (name, icon) ->
			val isSelected = name == selectedIconName

			CircularIcon(
				imageVector = icon,
				onClick = { onIconSelected(name) },
				circleSize = 56.dp,
				iconSize = 24.dp,
				backgroundColor = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
				else MaterialTheme.colorScheme.surfaceVariant,
				tint = if (isSelected) MaterialTheme.colorScheme.primary
				else MaterialTheme.colorScheme.onSurfaceVariant,
				contentDescription = name
			)
		}
	}
}
