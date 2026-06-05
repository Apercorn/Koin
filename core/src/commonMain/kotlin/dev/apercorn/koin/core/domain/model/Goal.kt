package dev.apercorn.koin.core.domain.model

import kotlinx.datetime.LocalDate

data class Goal(
	/** Unique identifier for the goal */
	val id: String,
	/** Name/title of the savings goal */
	val name: String,
	/** Target amount to save in cents/smallest currency unit */
	val targetAmount: Long,
	/** Current amount saved towards the goal in cents/smallest currency unit */
	val currentAmount: Long = 0,
	/** ISO currency code for the goal */
	val currency: String,
	/** Optional deadline date for achieving the goal */
	val deadline: LocalDate? = null,
	/** Optional ID of the account linked to this goal for tracking */
	val linkedAccountId: String? = null
) {
	val progress: Float
		get() = if (targetAmount > 0) (currentAmount.toFloat() / targetAmount).coerceIn(0f, 1f) else 0f
}