package dev.apercorn.koin.core.data.database.entities

import androidx.room.*

@Entity(
	tableName = "budgets",
	foreignKeys = [
		ForeignKey(
			entity = CategoryEntity::class,
			parentColumns = ["id"],
			childColumns = ["categoryId"],
			onDelete = ForeignKey.CASCADE
		)
	],
	indices = [Index(value = ["categoryId"])]
)
data class BudgetEntity(
	@PrimaryKey val id: String,
	val categoryId: String,
	val monthlyCap: Long,
	val currency: String,
	val rollover: Boolean = false,
	val createdAt: Long
)