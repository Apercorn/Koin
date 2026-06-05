package dev.apercorn.koin.core.domain.model

data class Budget(
	/** Unique identifier for the budget */
	val id: String,
	/** ID of the category this budget applies to */
	val categoryId: String,
	/** Maximum spending cap for the month in cents/smallest currency unit */
	val monthlyCap: Long,
	/** ISO currency code for the budget */
	val currency: String,
	/** Whether unspent amount rolls over to the next month */
	val rollover: Boolean = false,
	/** Current month's spending in cents/smallest currency unit */
	val currentSpending: Long = 0
) {
	val spentPercentage: Float
		get() = if (monthlyCap > 0) (currentSpending.toFloat() / monthlyCap).coerceIn(0f, 1f) else 0f

	val remaining: Long
		get() = (monthlyCap - currentSpending).coerceAtLeast(0)
}