package dev.apercorn.koin.core.domain.usecase

import dev.apercorn.koin.core.data.repository.AccountRepository

class DeleteAccountUseCase(
	private val accountRepository: AccountRepository
) {
	sealed class Result {
		data object Success : Result()
		data class Error(val message: String) : Result()
	}

	suspend operator fun invoke(accountId: String): Result {
		val count = accountRepository.getTransactionCount(accountId)
		if (count > 0) {
			return Result.Error("Cannot delete account with $count transaction(s). Move or delete them first.")
		}
		accountRepository.delete(accountId)
		return Result.Success
	}
}