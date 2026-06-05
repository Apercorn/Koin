package dev.apercorn.koin.core.domain.usecase

import dev.apercorn.koin.core.data.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class SpendingData(
	val categoryId: String?,
	val total: Long
)

class GetSpendingByPeriodUseCase(
	private val transactionRepository: TransactionRepository
) {
	operator fun invoke(startDate: String, endDate: String): Flow<List<SpendingData>> {
		return transactionRepository.getSpendingByCategory(startDate, endDate).map { list ->
			list.map { SpendingData(categoryId = it.categoryId, total = it.total) }
		}
	}

	fun getTotalExpenses(startDate: String, endDate: String): Flow<Long> {
		return transactionRepository.getTotalExpensesInPeriod(startDate, endDate)
	}

	fun getTotalIncome(startDate: String, endDate: String): Flow<Long> {
		return transactionRepository.getTotalIncomeInPeriod(startDate, endDate)
	}
}