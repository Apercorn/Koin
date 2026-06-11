package dev.apercorn.koin.core.data.database.entities

import androidx.room.*

@Entity(
	tableName = "transactions",
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
		Index(value = ["categoryId"]),
		Index(value = ["date"])
	]
)
data class TransactionEntity(
	@PrimaryKey val id: String,
	val accountId: String,
	val categoryId: String? = null,
	val counterpartyId: String? = null,
	val amount: Long, // stored in minor units
	val currency: String,
	val type: String, // INCOME, EXPENSE, TRANSFER
	val date: String, // ISO LocalDate string
	val note: String? = null,
	val isRecurring: Boolean = false,
	val recurringId: String? = null,
	val createdAt: Long,
	val updatedAt: Long
)