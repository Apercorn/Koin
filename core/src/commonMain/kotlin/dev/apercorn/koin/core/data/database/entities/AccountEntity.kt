package dev.apercorn.koin.core.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class AccountEntity(
	@PrimaryKey val id: String,
	val name: String,
	val description: String? = null,
	val type: String,
	val currency: String,
	val iconName: String,
	val colorHex: String,
	val isPrimary: Boolean = false,
	val createdAt: Long,
	val updatedAt: Long
)