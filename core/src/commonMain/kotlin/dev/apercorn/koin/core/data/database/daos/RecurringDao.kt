package dev.apercorn.koin.core.data.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.apercorn.koin.core.data.database.entities.RecurringEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecurringDao {
	@Query("SELECT * FROM recurring WHERE isActive = 1 ORDER BY nextDueDate ASC")
	fun getActiveRecurring(): Flow<List<RecurringEntity>>

	@Query("SELECT * FROM recurring ORDER BY createdAt DESC")
	fun getAllRecurring(): Flow<List<RecurringEntity>>

	@Query("SELECT * FROM recurring WHERE id = :id")
	suspend fun getRecurringById(id: String): RecurringEntity?

	@Query(
		"SELECT * FROM recurring WHERE isActive = 1 AND nextDueDate <= :untilDate"
	)
	suspend fun getDueRecurring(untilDate: String): List<RecurringEntity>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsert(recurring: RecurringEntity)

	@Update
	suspend fun update(recurring: RecurringEntity)

	@Delete
	suspend fun delete(recurring: RecurringEntity)

	@Query("DELETE FROM recurring WHERE id = :id")
	suspend fun deleteById(id: String)
}