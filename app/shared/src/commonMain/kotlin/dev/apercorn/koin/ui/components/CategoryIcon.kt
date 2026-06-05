package dev.apercorn.koin.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CategoryIcon(
	iconName: String,
	name: String,
	modifier: Modifier = Modifier
) {
	Column(
		modifier = modifier,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Surface(
			modifier = Modifier.size(48.dp),
			shape = MaterialTheme.shapes.medium,
			color = MaterialTheme.colorScheme.surfaceVariant
		) {
			Box(contentAlignment = Alignment.Center) {
				Text(
					text = iconName.take(2),
					style = MaterialTheme.typography.titleLarge
				)
			}
		}
		Spacer(modifier = Modifier.height(4.dp))
		Text(
			text = name,
			style = MaterialTheme.typography.bodySmall,
			fontWeight = FontWeight.Medium,
			maxLines = 1
		)
	}
}