package dev.apercorn.koin.ui.screens.transactions.entry

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Lightweight UI representation of an account or category for the party picker cards.
 */
data class PartyUiModel(
	val id: String,
	val label: String,          // display name
	val icon: ImageVector,      // resolved from account/category type
	val roleTag: String         // "ACCOUNT" | "EXPENSE" | "INCOME" | "TRANSFER"
)
