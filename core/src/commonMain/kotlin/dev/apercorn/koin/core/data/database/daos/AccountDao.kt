package dev.apercorn.koin.core.data.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.apercorn.koin.core.data.database.entities.AccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
	@Query("SELECT * FROM accounts ORDER BY isPrimary DESC, name ASC")
	fun getAllAccounts(): Flow<List<AccountEntity>>

	@Query("SELECT * FROM accounts WHERE id = :id")
	suspend fun getAccountById(id: String): AccountEntity?

	@Query("SELECT * FROM accounts WHERE id = :id")
	fun getAccountByIdFlow(id: String): Flow<AccountEntity?>

	@Query("SELECT * FROM accounts WHERE isPrimary = 1 LIMIT 1")
	suspend fun getPrimaryAccount(): AccountEntity?

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsert(account: AccountEntity)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsertAll(accounts: List<AccountEntity>)

	@Update
	suspend fun update(account: AccountEntity)

	@Delete
	suspend fun delete(account: AccountEntity)

	@Query("DELETE FROM accounts WHERE id = :id")
	suspend fun deleteById(id: String)

	@Query("SELECT COUNT(*) FROM transactions WHERE accountId = :accountId")
	suspend fun getTransactionCount(accountId: String): Int
}