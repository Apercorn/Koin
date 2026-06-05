package dev.apercorn.koin.core.data.repository

import dev.apercorn.koin.core.domain.model.Account
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
	fun getAllAccounts(): Flow<List<Account>>
	fun getAccountById(id: String): Flow<Account?>
	suspend fun getAccountByIdOnce(id: String): Account?
	suspend fun getPrimaryAccount(): Account?
	suspend fun save(account: Account)
	suspend fun delete(id: String)
	suspend fun getTransactionCount(accountId: String): Int
}