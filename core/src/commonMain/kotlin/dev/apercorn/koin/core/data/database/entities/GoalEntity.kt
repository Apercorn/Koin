package dev.apercorn.koin.core.data.database.entities

import androidx.room.*

@Entity(
	tableName = "goals",
	foreignKeys = [
		ForeignKey(
			entity = AccountEntity::class,
			parentColumns = ["id"],
			childColumns = ["linkedAccountId"],
			onDelete = ForeignKey.SET_NULL
		)
	],
	indices = [Index(value = ["linkedAccountId"])]
)
data class GoalEntity(
	@PrimaryKey val id: String,
	val name: String,
	val targetAmount: Long, // minor units
	val currentAmount: Long = 0,
	val currency: String,
	val deadline: String? = null, // ISO LocalDate string
	val linkedAccountId: String? = null,
	val createdAt: Long
)