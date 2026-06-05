package dev.apercorn.koin.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * Layout boilerplate for screens
 */
@Composable
fun ScreenLayout(
	modifier: Modifier = Modifier,
	topBar: @Composable () -> Unit = { TopBar(title = "Screen") },
	isLoading: Boolean = false,
	loadingContent: @Composable () -> Unit = {
		Box(
			modifier = Modifier.fillMaxSize(),
			contentAlignment = Alignment.Center
		) {
			CircularProgressIndicator()
		}
	},
	content: @Composable ColumnScope.() -> Unit
) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(horizontal = 20.dp)
	) {
		topBar()

		Spacer(modifier = Modifier.height(16.dp))

		if (isLoading) {
			loadingContent()
		} else {
			content()
		}
	}
}

@Composable
fun TopBar(
	title: String,
	modifier: Modifier = Modifier,
	actions: List<@Composable () -> Unit> = emptyList()
) {
	Row(
		modifier = modifier
			.fillMaxWidth()
			.padding(vertical = 8.dp)
			.heightIn(min = 56.dp),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.SpaceBetween
	) {
		Text(
			text = title,
			style = MaterialTheme.typography.headlineMedium,
			fontWeight = FontWeight.Bold,
			color = MaterialTheme.colorScheme.onBackground
		)

		if (actions.isNotEmpty()) {
			Surface(
				shape = CircleShape,
				color = MaterialTheme.colorScheme.surface,
				border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
			) {
				CompositionLocalProvider(LocalRippleConfiguration provides null) {
					Row(
						modifier = Modifier.padding(horizontal = 0.dp, vertical = 0.dp),
						horizontalArrangement = Arrangement.spacedBy(4.dp),
						verticalAlignment = Alignment.CenterVertically
					) {
						actions.forEach { action ->
							action()
						}
					}
				}
			}
		}
	}
}