package dev.apercorn.koin.core.data.database.entities

import androidx.room.*

@Entity(
	tableName = "recurring",
	foreignKeys = [
		ForeignKey(
			entity = AccountEntity::class,
			parentColumns = ["id"],
			childColumns = ["accountId"],
			onDelete = ForeignKey.CASCADE
		),
		ForeignKey(
			entity = CategoryEntity::class,
			parentColumns = ["id"],
			childColumns = ["categoryId"],
			onDelete = ForeignKey.SET_NULL
		)
	],
	indices = [
		Index(value = ["accountId"]),
		Index(value = ["categoryId"])
	]
)
data class RecurringEntity(
	@PrimaryKey val id: String,
	val accountId: String,
	val categoryId: String? = null,
	val amount: Long,
	val currency: String,
	val type: String, // INCOME, EXPENSE, TRANSFER
	val note: String? = null,
	val schedule: String,
	val customIntervalDays: Int? = null,
	val lastProcessedDate: String? = null, // ISO LocalDate
	val nextDueDate: String, // ISO LocalDate
	val isActive: Boolean = true,
	val createdAt: Long
)