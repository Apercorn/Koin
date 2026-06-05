package dev.apercorn.koin.core.domain.usecase

import com.benasher44.uuid.uuid4
import dev.apercorn.koin.core.data.repository.AccountRepository
import dev.apercorn.koin.core.data.repository.TransactionRepository
import dev.apercorn.koin.core.domain.model.Transaction
import dev.apercorn.koin.core.domain.model.TransactionType
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class AddTransactionUseCase(
	private val transactionRepository: TransactionRepository,
	private val accountRepository: AccountRepository
) {
	sealed class Result {
		data object Success : Result()
		data class Error(val message: String) : Result()
	}

	suspend operator fun invoke(
		accountId: String,
		amount: Long,
		type: TransactionType,
		currency: String,
		categoryId: String? = null,
		note: String? = null,
		date: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
	): Result {
		if (amount <= 0) return Result.Error("Amount must be positive")
		if (accountId.isBlank()) return Result.Error("Account is required")

		val account = accountRepository.getAccountByIdOnce(accountId)
		if (account == null) return Result.Error("Account not found")

		val transaction = Transaction.OneOff(
			id = uuid4().toString(),
			accountId = accountId,
			categoryId = categoryId,
			amount = amount,
			currency = currency,
			type = type,
			date = date,
			note = note
		)

		transactionRepository.save(transaction)
		return Result.Success
	}
}