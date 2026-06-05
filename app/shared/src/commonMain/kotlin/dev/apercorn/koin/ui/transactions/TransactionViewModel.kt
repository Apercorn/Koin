package dev.apercorn.koin.ui.transactions

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import dev.apercorn.koin.core.data.repository.AccountRepository
import dev.apercorn.koin.core.data.repository.CategoryRepository
import dev.apercorn.koin.core.data.repository.TransactionRepository
import dev.apercorn.koin.core.domain.model.Transaction
import dev.apercorn.koin.core.domain.model.TransactionType
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class TransactionScreenState(
	val transactions: List<Transaction> = emptyList(),
	val filterType: TransactionType? = null,
	val filterAccountId: String? = null,
	val searchQuery: String = "",
	val isLoading: Boolean = true
)

class TransactionViewModel(
	private val transactionRepository: TransactionRepository,
	private val accountRepository: AccountRepository,
	private val categoryRepository: CategoryRepository
) : ScreenModel {

	private val _state = MutableStateFlow(TransactionScreenState())
	val state: StateFlow<TransactionScreenState> = _state.asStateFlow()

	init {
		loadTransactions()
	}

	private fun loadTransactions() {
		screenModelScope.launch {
			transactionRepository.getAllTransactions().collect { transactions ->
				_state.update { it.copy(transactions = transactions, isLoading = false) }
			}
		}
	}

	fun setFilterType(type: TransactionType?) {
		_state.update { it.copy(filterType = type) }
	}

	fun setFilterAccount(accountId: String?) {
		_state.update { it.copy(filterAccountId = accountId) }
	}

	fun setSearchQuery(query: String) {
		_state.update { it.copy(searchQuery = query) }
	}

	fun deleteTransaction(id: String) {
		screenModelScope.launch {
			transactionRepository.delete(id)
		}
	}
}