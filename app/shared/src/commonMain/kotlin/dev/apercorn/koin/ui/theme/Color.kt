package dev.apercorn.koin.ui.theme

import androidx.compose.ui.graphics.Color

// Dark-first palette with blue and green accents
object KoinColors {
	// Accent — Blue (primary actions, interactive elements)
	val Blue = Color(0xFF3B82F6)
	val BlueLight = Color(0xFF60A5FA)
	val BlueDark = Color(0xFF1D4ED8)
	val BlueContainer = Color(0xFF1E3A5F)

	// Accent — Green (income, positive indicators, chart)
	val Green = Color(0xFF22C55E)
	val GreenLight = Color(0xFF4ADE80)
	val GreenDark = Color(0xFF16A34A)
	val GreenContainer = Color(0xFF14532D)

	// Background
	val DarkBackground = Color(0xFF090909)
	val DarkSurface = Color(0xFF151515)
	val DarkSurfaceVariant = Color(0xFF1D1D1E)
	val LightBackground = Color(0xFFF5F5F5)
	val LightSurface = Color(0xFFFFFFFF)
	val LightSurfaceVariant = Color(0xFFE8E8E8)

	// Text
	val DarkOnBackground = Color(0xFFE0E0E0)
	val DarkOnSurface = Color(0xFFFFFFFF)
	val DarkOnSurfaceVariant = Color(0xFF9CA3AF)
	val LightOnBackground = Color(0xFF1C1B1F)
	val LightOnSurface = Color(0xFF1C1B1F)
	val LightOnSurfaceVariant = Color(0xFF6B7280)

	// Border / Outline
	val DarkBorder = Color(0xD51A1A1A)
	val DarkBorderVariant = Color(0xFF3A3A3A)
	val LightBorder = Color(0xFFD1D5DB)
	val LightBorderVariant = Color(0xFFE5E7EB)

	// Semantic
	val Income = Color(0xFF22C55E)
	val Expense = Color(0xFFEF4444)
	val Transfer = Color(0xFF3B82F6)
	val Warning = Color(0xFFF59E0B)
	val Success = Color(0xFF22C55E)
	val Error = Color(0xFFEF4444)
}