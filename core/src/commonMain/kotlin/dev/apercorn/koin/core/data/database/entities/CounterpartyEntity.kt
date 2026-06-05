package dev.apercorn.koin.core.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "counterparties")
data class CounterpartyEntity(
	@PrimaryKey val id: String,
	val name: String,
	val email: String? = null,
	val phone: String? = null,
	val notes: String? = null,
	val createdAt: Long,
	val updatedAt: Long
)