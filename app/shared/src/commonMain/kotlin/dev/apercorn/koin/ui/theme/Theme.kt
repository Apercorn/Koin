package dev.apercorn.koin.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*

@Composable
fun KoinTheme(
	darkTheme: Boolean = isSystemInDarkTheme(),
	content: @Composable () -> Unit
) {
	val koinColorScheme = if (darkTheme) DarkKoinColorScheme else LightKoinColorScheme
	val customColors = if (darkTheme) DarkKoinCustomColors else LightKoinCustomColors

	CompositionLocalProvider(
		LocalKoinCustomColors provides customColors
	) {
		MaterialTheme(
			colorScheme = koinColorScheme,
			typography = koinTypography(),
			content = content
		)
	}
}

object KoinTheme {
	val colors: KoinCustomColors
		@Composable
		@ReadOnlyComposable
		get() = LocalKoinCustomColors.current
}
