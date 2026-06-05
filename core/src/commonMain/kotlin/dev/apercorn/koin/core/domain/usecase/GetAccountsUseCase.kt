package dev.apercorn.koin.core.domain.usecase

import dev.apercorn.koin.core.data.repository.AccountRepository
import dev.apercorn.koin.core.data.repository.TransactionRepository
import dev.apercorn.koin.core.domain.model.Account
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

data class AccountsState(
	val totalBalance: Long = 0,
	val accounts: List<Account> = emptyList()
)

class GetAccountsUseCase(
	private val accountRepository: AccountRepository,
	private val transactionRepository: TransactionRepository
) {
	operator fun invoke(): Flow<AccountsState> {
		return combine(
			accountRepository.getAllAccounts(),
			transactionRepository.getTotalBalance()
		) { accounts, totalBalance ->
			AccountsState(
				totalBalance = totalBalance,
				accounts = accounts
			)
		}
	}
}