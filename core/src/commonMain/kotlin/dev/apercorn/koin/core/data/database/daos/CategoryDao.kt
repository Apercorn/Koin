package dev.apercorn.koin.core.data.database.daos

import androidx.room.*
import dev.apercorn.koin.core.data.database.entities.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
	@Query("SELECT * FROM categories ORDER BY sortOrder ASC, name ASC")
	fun getAllCategories(): Flow<List<CategoryEntity>>

	@Query("SELECT * FROM categories WHERE type = :type ORDER BY sortOrder ASC, name ASC")
	fun getCategoriesByType(type: String): Flow<List<CategoryEntity>>

	@Query("SELECT * FROM categories WHERE id = :id")
	suspend fun getCategoryById(id: String): CategoryEntity?

	@Query("SELECT * FROM categories WHERE id = :id")
	fun getCategoryByIdFlow(id: String): Flow<CategoryEntity?>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsert(category: CategoryEntity)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsertAll(categories: List<CategoryEntity>)

	@Update
	suspend fun update(category: CategoryEntity)

	@Delete
	suspend fun delete(category: CategoryEntity)

	@Query("DELETE FROM categories WHERE id = :id")
	suspend fun deleteById(id: String)

	@Query("SELECT COUNT(*) FROM categories")
	suspend fun count(): Int
}