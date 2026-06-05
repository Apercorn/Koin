package dev.apercorn.koin.ui.screens.overview

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import dev.apercorn.koin.core.data.repository.TransactionRepository
import dev.apercorn.koin.core.domain.usecase.GetAccountsUseCase
import dev.apercorn.koin.core.domain.usecase.GetSpendingByPeriodUseCase
import dev.apercorn.koin.core.util.DateUtils
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class OverviewState(
	val totalBalance: Long = 0,
	val monthlyIncome: Long = 0,
	val monthlyExpenses: Long = 0,
	val baseCurrency: String = "USD",
	val recentTransactions: List<dev.apercorn.koin.core.domain.model.Transaction> = emptyList(),
	val spendingByCategory: List<SpendingCategoryItem> = emptyList(),
	val isLoading: Boolean = true
)

data class SpendingCategoryItem(
	val categoryId: String?,
	val total: Long
)

class OverviewViewModel(
	private val getAccountsUseCase: GetAccountsUseCase,
	private val transactionRepository: TransactionRepository,
	private val getSpendingByPeriodUseCase: GetSpendingByPeriodUseCase
) : ScreenModel {

	private val _state = MutableStateFlow(OverviewState())
	val state: StateFlow<OverviewState> = _state.asStateFlow()

	init {
		loadData()
	}

	private fun loadData() {
		screenModelScope.launch {
			val ym = DateUtils.currentYearMonth()
			val startDate = "${ym}-01"
			val parts = ym.split("-")
			val year = parts[0].toInt()
			val monthNum = parts[1].toInt()
			val endDate = DateUtils.endOfMonth(year, monthNum).toString()

			// Collect total balance
			getAccountsUseCase().collect { accountsState ->
				_state.update { it.copy(totalBalance = accountsState.totalBalance) }
			}
		}

		screenModelScope.launch {
			val ym = DateUtils.currentYearMonth()
			val startDate = "${ym}-01"
			val parts = ym.split("-")
			val year = parts[0].toInt()
			val monthNum = parts[1].toInt()
			val endDate = DateUtils.endOfMonth(year, monthNum).toString()

			getSpendingByPeriodUseCase.getTotalIncome(startDate, endDate).collect { income ->
				_state.update { it.copy(monthlyIncome = income) }
			}
		}

		screenModelScope.launch {
			val ym = DateUtils.currentYearMonth()
			val startDate = "${ym}-01"
			val parts = ym.split("-")
			val year = parts[0].toInt()
			val monthNum = parts[1].toInt()
			val endDate = DateUtils.endOfMonth(year, monthNum).toString()

			getSpendingByPeriodUseCase.getTotalExpenses(startDate, endDate).collect { expenses ->
				_state.update { it.copy(monthlyExpenses = expenses) }
			}
		}

		screenModelScope.launch {
			val ym = DateUtils.currentYearMonth()
			val startDate = "${ym}-01"
			val parts = ym.split("-")
			val year = parts[0].toInt()
			val monthNum = parts[1].toInt()
			val endDate = DateUtils.endOfMonth(year, monthNum).toString()

			getSpendingByPeriodUseCase(startDate, endDate).collect { spending ->
				_state.update {
					it.copy(
						spendingByCategory = spending.map { s ->
							SpendingCategoryItem(categoryId = s.categoryId, total = s.total)
						}
					)
				}
			}
		}

		screenModelScope.launch {
			transactionRepository.getAllTransactions().collect { transactions ->
				_state.update {
					it.copy(
						recentTransactions = transactions.take(5),
						isLoading = false
					)
				}
			}
		}
	}
}