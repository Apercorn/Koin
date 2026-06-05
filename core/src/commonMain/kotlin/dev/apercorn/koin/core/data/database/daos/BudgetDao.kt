package dev.apercorn.koin.core.data.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.apercorn.koin.core.data.database.entities.BudgetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetDao {
	@Query("SELECT * FROM budgets ORDER BY createdAt DESC")
	fun getAllBudgets(): Flow<List<BudgetEntity>>

	@Query("SELECT * FROM budgets WHERE id = :id")
	suspend fun getBudgetById(id: String): BudgetEntity?

	@Query("SELECT * FROM budgets WHERE categoryId = :categoryId")
	suspend fun getBudgetByCategory(categoryId: String): BudgetEntity?

	@Query("SELECT * FROM budgets WHERE categoryId = :categoryId")
	fun getBudgetByCategoryFlow(categoryId: String): Flow<BudgetEntity?>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsert(budget: BudgetEntity)

	@Update
	suspend fun update(budget: BudgetEntity)

	@Delete
	suspend fun delete(budget: BudgetEntity)

	@Query("DELETE FROM budgets WHERE id = :id")
	suspend fun deleteById(id: String)
}