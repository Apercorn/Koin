package dev.apercorn.koin.core.domain.usecase

import dev.apercorn.koin.core.data.repository.BudgetRepository
import dev.apercorn.koin.core.data.repository.TransactionRepository
import dev.apercorn.koin.core.domain.model.Budget
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

data class BudgetWithSpending(
	val budget: Budget,
	val currentSpending: Long,
	val percentage: Float
)

class CheckBudgetStatusUseCase(
	private val budgetRepository: BudgetRepository,
	private val transactionRepository: TransactionRepository
) {
	operator fun invoke(yearMonth: String): Flow<List<BudgetWithSpending>> {
		val startDate = "${yearMonth}-01"
		// Calculate end date (simplified - use last day of month)
		val parts = yearMonth.split("-")
		val year = parts[0].toInt()
		val month = parts[1].toInt()
		val lastDay = when (month) {
			1, 3, 5, 7, 8, 10, 12 -> 31
			4, 6, 9, 11 -> 30
			2 -> if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) 29 else 28
			else -> 30
		}
		val endDate = "${yearMonth}-${lastDay}"

		return budgetRepository.getAllBudgets().combine(
			transactionRepository.getSpendingByCategory(startDate, endDate)
		) { budgets, spendingList ->
			budgets.map { budget ->
				val spent = spendingList
					.find { it.categoryId == budget.categoryId }
					?.total ?: 0L
				BudgetWithSpending(
					budget = budget.copy(currentSpending = spent),
					currentSpending = spent,
					percentage = if (budget.monthlyCap > 0) (spent.toFloat() / budget.monthlyCap).coerceIn(
						0f,
						1f
					) else 0f
				)
			}
		}
	}
}