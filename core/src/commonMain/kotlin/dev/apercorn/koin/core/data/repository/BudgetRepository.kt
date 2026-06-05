package dev.apercorn.koin.core.data.repository

import dev.apercorn.koin.core.domain.model.Budget
import kotlinx.coroutines.flow.Flow

interface BudgetRepository {
	fun getAllBudgets(): Flow<List<Budget>>
	suspend fun getBudgetById(id: String): Budget?
	suspend fun save(budget: Budget)
	suspend fun delete(id: String)
}