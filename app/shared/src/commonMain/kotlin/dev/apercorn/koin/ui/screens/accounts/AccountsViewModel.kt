package dev.apercorn.koin.ui.screens.accounts

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
	val totalBalance: Long = 0L,
	val currency: String = "USD",
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
		loadTotalBalance()
	}

	private fun loadAccounts() {
		screenModelScope.launch {
			accountRepository.getAllAccounts()
				.flatMapLatest { accounts ->
					if (accounts.isEmpty()) {
						flowOf(emptyList())
					} else {
						combine(
							accounts.map { account ->
								transactionRepository.getBalanceForAccount(account.id).map { balance ->
									AccountWithBalance(account = account, balance = balance)
								}
							}
						) { it.toList() }
					}
				}
				.collect { withBalances ->
					_state.update { it.copy(accounts = withBalances, isLoading = false) }
				}
		}
	}

	private fun loadTotalBalance() {
		screenModelScope.launch {
			transactionRepository.getTotalBalance().collect { total ->
				_state.update { it.copy(totalBalance = total) }
			}
		}
	}

	fun addAccount(
		name: String,
		description: String?,
		type: AccountType,
		currency: String,
		initialBalanceCents: Long,
		iconName: String,
		colorHex: String
	) {
		screenModelScope.launch {
			val account = Account(
				id = com.benasher44.uuid.uuid4().toString(),
				name = name,
				description = description,
				type = type,
				currency = currency,
				balance = initialBalanceCents,
				iconName = iconName,
				colorHex = colorHex
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