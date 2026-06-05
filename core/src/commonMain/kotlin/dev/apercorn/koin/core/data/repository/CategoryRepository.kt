package dev.apercorn.koin.core.data.repository

import dev.apercorn.koin.core.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
	fun getAllCategories(): Flow<List<Category>>
	fun getCategoriesByType(type: String): Flow<List<Category>>
	suspend fun getCategoryById(id: String): Category?
	fun getCategoryByIdFlow(id: String): Flow<Category?>
	suspend fun save(category: Category)
	suspend fun delete(id: String)
}