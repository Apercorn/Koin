package dev.apercorn.koin.ui.accounts

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import dev.apercorn.koin.core.data.repository.AccountRepository
import dev.apercorn.koin.core.data.repository.TransactionRepository
import dev.apercorn.koin.core.domain.model.Account
import dev.apercorn.koin.core.domain.model.AccountType
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class AccountsState(
	val accounts: List<AccountWithBalance> = emptyList(),
	val isLoading: Boolean = true
)

data class AccountWithBalance(
	val account: Account,
	val balance: Long
)

class AccountsViewModel(
	private val accountRepository: AccountRepository,
	private val transactionRepository: TransactionRepository
) : ScreenModel {

	private val _state = MutableStateFlow(AccountsState())
	val state: StateFlow<AccountsState> = _state.asStateFlow()

	init {
		loadAccounts()
	}

	private fun loadAccounts() {
		screenModelScope.launch {
			accountRepository.getAllAccounts().collect { accounts ->
				val withBalances = accounts.map { account ->
					AccountWithBalance(account = account, balance = 0L)
				}
				_state.update { it.copy(accounts = withBalances, isLoading = false) }
			}
		}
	}

	fun addAccount(name: String, type: AccountType, currency: String = "USD") {
		screenModelScope.launch {
			val account = Account(
				id = com.benasher44.uuid.uuid4().toString(),
				name = name,
				type = type,
				currency = currency,
				balance = 0L,
				iconName = "account_balance", // Default icon
				colorHex = "#7C4DFF" // Default brand color
			)
			accountRepository.save(account)
		}
	}

	fun deleteAccount(id: String) {
		screenModelScope.launch {
			accountRepository.delete(id)
		}
	}
}