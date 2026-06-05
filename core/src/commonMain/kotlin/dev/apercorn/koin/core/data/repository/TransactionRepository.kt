package dev.apercorn.koin.core.data.repository

import dev.apercorn.koin.core.data.database.daos.CategorySpending
import dev.apercorn.koin.core.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
	fun getAllTransactions(): Flow<List<Transaction>>
	fun getTransactionsByAccount(accountId: String): Flow<List<Transaction>>
	fun getTransactionsFiltered(
		accountId: String? = null,
		categoryId: String? = null,
		startDate: String? = null,
		endDate: String? = null,
		type: String? = null
	): Flow<List<Transaction>>

	suspend fun getTransactionById(id: String): Transaction?
	suspend fun save(transaction: Transaction)
	suspend fun delete(id: String)

	fun getBalanceForAccount(accountId: String): Flow<Long>
	fun getTotalBalance(): Flow<Long>
	fun getSpendingByCategory(startDate: String, endDate: String): Flow<List<CategorySpending>>
	fun getTotalExpensesInPeriod(startDate: String, endDate: String): Flow<Long>
	fun getTotalIncomeInPeriod(startDate: String, endDate: String): Flow<Long>
}