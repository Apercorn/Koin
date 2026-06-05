package dev.apercorn.koin.core.data.repository

import dev.apercorn.koin.core.data.database.daos.CategoryDao
import dev.apercorn.koin.core.data.database.entities.CategoryEntity
import dev.apercorn.koin.core.domain.model.Category
import dev.apercorn.koin.core.domain.model.CategoryType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoryRepositoryImpl(
	private val categoryDao: CategoryDao
) : CategoryRepository {

	override fun getAllCategories(): Flow<List<Category>> {
		return categoryDao.getAllCategories().map { entities ->
			entities.map { it.toDomain() }
		}
	}

	override fun getCategoriesByType(type: String): Flow<List<Category>> {
		return categoryDao.getCategoriesByType(type).map { entities ->
			entities.map { it.toDomain() }
		}
	}

	override suspend fun getCategoryById(id: String): Category? {
		return categoryDao.getCategoryById(id)?.toDomain()
	}

	override fun getCategoryByIdFlow(id: String): Flow<Category?> {
		return categoryDao.getCategoryByIdFlow(id).map { it?.toDomain() }
	}

	override suspend fun save(category: Category) {
		categoryDao.upsert(category.toEntity())
	}

	override suspend fun delete(id: String) {
		categoryDao.deleteById(id)
	}

	private fun CategoryEntity.toDomain(): Category {
		return Category(
			id = id,
			name = name,
			iconName = iconName,
			colorHex = colorHex,
			type = CategoryType.fromString(type),
			sortOrder = sortOrder
		)
	}

	private fun Category.toEntity(): CategoryEntity {
		return CategoryEntity(
			id = id,
			name = name,
			iconName = iconName,
			colorHex = colorHex,
			type = CategoryType.toString(type),
			sortOrder = sortOrder
		)
	}
}