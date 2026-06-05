package dev.apercorn.koin.core.domain.usecase

import com.benasher44.uuid.uuid4
import dev.apercorn.koin.core.data.database.daos.RecurringDao
import dev.apercorn.koin.core.data.database.daos.TransactionDao
import dev.apercorn.koin.core.data.database.entities.RecurringEntity
import dev.apercorn.koin.core.data.database.entities.TransactionEntity
import kotlinx.datetime.*

class ProcessRecurringUseCase(
	private val recurringDao: RecurringDao,
	private val transactionDao: TransactionDao
) {
	suspend operator fun invoke(): Int {
		val today = Clock.System.todayIn(TimeZone.currentSystemDefault()).toString()
		val due = recurringDao.getDueRecurring(today)
		var count = 0

		for (recurring in due) {
			val transaction = TransactionEntity(
				id = uuid4().toString(),
				accountId = recurring.accountId,
				categoryId = recurring.categoryId,
				amount = recurring.amount,
				currency = recurring.currency,
				type = recurring.type,
				date = today,
				note = recurring.note,
				isRecurring = true,
				recurringId = recurring.id,
				createdAt = Clock.System.now().toEpochMilliseconds(),
				updatedAt = Clock.System.now().toEpochMilliseconds()
			)
			transactionDao.insert(transaction)

			val nextDue = calculateNextDue(LocalDate.parse(today), recurring)
			recurringDao.update(
				recurring.copy(
					lastProcessedDate = today,
					nextDueDate = nextDue.toString()
				)
			)
			count++
		}

		return count
	}

	private fun calculateNextDue(from: LocalDate, recurring: RecurringEntity): LocalDate {
		val daysToAdd = when (recurring.schedule) {
			"DAILY" -> 1
			"WEEKLY" -> 7
			"MONTHLY" -> 30
			"CUSTOM" -> recurring.customIntervalDays ?: 1
			else -> 30
		}
		return LocalDate.fromEpochDays(from.toEpochDays() + daysToAdd)
	}
}