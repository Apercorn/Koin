package dev.apercorn.koin.core.data.repository

import dev.apercorn.koin.core.data.database.daos.BudgetDao
import dev.apercorn.koin.core.data.database.entities.BudgetEntity
import dev.apercorn.koin.core.domain.model.Budget
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BudgetRepositoryImpl(
	private val budgetDao: BudgetDao
) : BudgetRepository {

	override fun getAllBudgets(): Flow<List<Budget>> {
		return budgetDao.getAllBudgets().map { entities ->
			entities.map { it.toDomain() }
		}
	}

	override suspend fun getBudgetById(id: String): Budget? {
		return budgetDao.getBudgetById(id)?.toDomain()
	}

	override suspend fun save(budget: Budget) {
		budgetDao.upsert(budget.toEntity())
	}

	override suspend fun delete(id: String) {
		budgetDao.deleteById(id)
	}

	private fun BudgetEntity.toDomain(): Budget {
		return Budget(
			id = id,
			categoryId = categoryId,
			monthlyCap = monthlyCap,
			currency = currency,
			rollover = rollover
		)
	}

	private fun Budget.toEntity(): BudgetEntity {
		return BudgetEntity(
			id = id,
			categoryId = categoryId,
			monthlyCap = monthlyCap,
			currency = currency,
			rollover = rollover,
			createdAt = kotlinx.datetime.Clock.System.now().toEpochMilliseconds()
		)
	}
}