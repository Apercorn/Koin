package dev.apercorn.koin.core.domain.model

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

data class RecurringTransaction(
	/** Unique identifier for the recurring transaction */
	val id: String,
	/** ID of the account this recurring transaction is linked to */
	val accountId: String,
	/** ID of the category */
	val categoryId: String? = null,
	/** Recurring amount in cents/smallest currency unit */
	val amount: Long,
	/** ISO currency code for the recurring transaction */
	val currency: String,
	/** Type of transaction (INCOME, EXPENSE, TRANSFER) */
	val type: TransactionType,
	/** Optional note or description for the recurring transaction */
	val note: String? = null,
	/** Recurrence schedule configuration */
	val schedule: RecurringSchedule,
	/** Date when the last occurrence was processed (null if never processed) */
	val lastProcessedDate: LocalDate? = null,
	/** Date when the next occurrence is due to be processed */
	val nextDueDate: LocalDate,
	/** Whether this recurring transaction is currently active */
	val isActive: Boolean = true
)

@Serializable
sealed interface RecurringSchedule {
	/** Repeats every X days */
	@Serializable
	data class Daily(val intervalDays: Int = 1) : RecurringSchedule

	/** Repeats every X weeks on specific days (e.g., Every 2 weeks on Tue, Thu) */
	@Serializable
	data class Weekly(
		val intervalWeeks: Int = 1,
		val daysOfWeek: Set<DayOfWeek>
	) : RecurringSchedule

	/** Repeats every X months on a specific day of the month (e.g., Every 3 months on the 15th) */
	@Serializable
	data class Monthly(
		val intervalMonths: Int = 1,
		val dayOfMonth: Int
	) : RecurringSchedule

	/** For arbitrary logic or Cron-like expressions */
	@Serializable
	data class Custom(val cronExpression: String) : RecurringSchedule
}