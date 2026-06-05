package dev.apercorn.koin.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen

object SettingsScreen : Screen {

	@Composable
	override fun Content() {
		Column(modifier = Modifier.fillMaxSize()) {
			Text(
				text = "Settings",
				style = MaterialTheme.typography.headlineMedium,
				fontWeight = FontWeight.Bold,
				modifier = Modifier.padding(16.dp)
			)

			Column(
				modifier = Modifier
					.fillMaxWidth()
					.padding(16.dp),
				verticalArrangement = Arrangement.spacedBy(16.dp)
			) {
				// Currency
				Card(modifier = Modifier.fillMaxWidth()) {
					Column(modifier = Modifier.padding(16.dp)) {
						Text(
							text = "Currency",
							style = MaterialTheme.typography.titleMedium,
							fontWeight = FontWeight.Medium
						)
						Spacer(modifier = Modifier.height(4.dp))
						Text(
							text = "USD - US Dollar",
							style = MaterialTheme.typography.bodyMedium,
							color = MaterialTheme.colorScheme.onSurfaceVariant
						)
					}
				}

				// Theme
				Card(modifier = Modifier.fillMaxWidth()) {
					Column(modifier = Modifier.padding(16.dp)) {
						Text(
							text = "Theme",
							style = MaterialTheme.typography.titleMedium,
							fontWeight = FontWeight.Medium
						)
						Spacer(modifier = Modifier.height(4.dp))
						Text(
							text = "System default (dark mode supported)",
							style = MaterialTheme.typography.bodyMedium,
							color = MaterialTheme.colorScheme.onSurfaceVariant
						)
					}
				}

				// About
				Card(modifier = Modifier.fillMaxWidth()) {
					Column(modifier = Modifier.padding(16.dp)) {
						Text(
							text = "About",
							style = MaterialTheme.typography.titleMedium,
							fontWeight = FontWeight.Medium
						)
						Spacer(modifier = Modifier.height(4.dp))
						Text(
							text = "Koin v1.0.0",
							style = MaterialTheme.typography.bodyMedium,
							color = MaterialTheme.colorScheme.onSurfaceVariant
						)
					}
				}
			}
		}
	}
}