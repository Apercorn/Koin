package dev.apercorn.koin.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
	primary = KoinColors.Blue,
	onPrimary = KoinColors.DarkOnSurface,
	primaryContainer = KoinColors.BlueContainer,
	onPrimaryContainer = KoinColors.BlueLight,
	secondary = KoinColors.Green,
	onSecondary = KoinColors.DarkOnSurface,
	secondaryContainer = KoinColors.GreenContainer,
	onSecondaryContainer = KoinColors.GreenLight,
	tertiary = KoinColors.Green,
	onTertiary = KoinColors.DarkOnSurface,
	tertiaryContainer = KoinColors.GreenContainer,
	onTertiaryContainer = KoinColors.GreenLight,
	background = KoinColors.DarkBackground,
	onBackground = KoinColors.DarkOnBackground,
	surface = KoinColors.DarkSurface,
	onSurface = KoinColors.DarkOnSurface,
	surfaceVariant = KoinColors.DarkSurfaceVariant,
	onSurfaceVariant = KoinColors.DarkOnSurfaceVariant,
	outline = KoinColors.DarkBorder,
	outlineVariant = KoinColors.DarkBorderVariant,
	error = KoinColors.Error,
	onError = KoinColors.DarkOnSurface
)

private val LightColorScheme = lightColorScheme(
	primary = KoinColors.Blue,
	onPrimary = KoinColors.LightOnSurface,
	primaryContainer = KoinColors.BlueLight,
	onPrimaryContainer = KoinColors.BlueDark,
	secondary = KoinColors.Green,
	onSecondary = KoinColors.LightOnSurface,
	secondaryContainer = KoinColors.GreenLight,
	onSecondaryContainer = KoinColors.GreenDark,
	tertiary = KoinColors.Green,
	onTertiary = KoinColors.LightOnSurface,
	tertiaryContainer = KoinColors.GreenLight,
	onTertiaryContainer = KoinColors.GreenDark,
	background = KoinColors.LightBackground,
	onBackground = KoinColors.LightOnBackground,
	surface = KoinColors.LightSurface,
	onSurface = KoinColors.LightOnSurface,
	surfaceVariant = KoinColors.LightSurfaceVariant,
	onSurfaceVariant = KoinColors.LightOnSurfaceVariant,
	outline = KoinColors.LightBorder,
	outlineVariant = KoinColors.LightBorderVariant,
	error = KoinColors.Error,
	onError = KoinColors.LightOnSurface
)

@Composable
fun KoinTheme(
	darkTheme: Boolean = isSystemInDarkTheme(),
	content: @Composable () -> Unit
) {
	val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

	MaterialTheme(
		colorScheme = colorScheme,
		typography = Typography(),
		content = content
	)
}