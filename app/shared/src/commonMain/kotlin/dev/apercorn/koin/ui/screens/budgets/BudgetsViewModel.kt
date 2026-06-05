package dev.apercorn.koin.ui.screens.budgets

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import dev.apercorn.koin.core.domain.usecase.CheckBudgetStatusUseCase
import dev.apercorn.koin.core.util.DateUtils
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class BudgetsState(
	val budgets: List<dev.apercorn.koin.core.domain.usecase.BudgetWithSpending> = emptyList(),
	val isLoading: Boolean = true
)

class BudgetsViewModel(
	private val checkBudgetStatusUseCase: CheckBudgetStatusUseCase
) : ScreenModel {

	private val _state = MutableStateFlow(BudgetsState())
	val state: StateFlow<BudgetsState> = _state.asStateFlow()

	init {
		loadBudgets()
	}

	private fun loadBudgets() {
		screenModelScope.launch {
			val ym = DateUtils.currentYearMonth()
			checkBudgetStatusUseCase(ym).collect { budgets ->
				_state.update { it.copy(budgets = budgets, isLoading = false) }
			}
		}
	}
}