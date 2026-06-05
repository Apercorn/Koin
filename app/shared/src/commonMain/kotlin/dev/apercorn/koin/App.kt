package dev.apercorn.koin

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.apercorn.koin.navigation.AppNavigator
import dev.apercorn.koin.ui.theme.KoinTheme

@Composable
fun App() {
	KoinTheme {
		Surface(
			modifier = Modifier.fillMaxSize(),
			color = MaterialTheme.colorScheme.background
		) {
			AppNavigator()
		}
	}
}
