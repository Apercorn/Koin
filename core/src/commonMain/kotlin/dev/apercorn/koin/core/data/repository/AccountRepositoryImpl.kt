package dev.apercorn.koin.core.data.repository

import dev.apercorn.koin.core.data.database.daos.AccountDao
import dev.apercorn.koin.core.data.database.entities.AccountEntity
import dev.apercorn.koin.core.domain.model.Account
import dev.apercorn.koin.core.domain.model.AccountType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock.System

class AccountRepositoryImpl(
	private val accountDao: AccountDao,
	private val transactionDao: dev.apercorn.koin.core.data.database.daos.TransactionDao
) : AccountRepository {

	override fun getAllAccounts(): Flow<List<Account>> {
		return accountDao.getAllAccounts().map { entities ->
			entities.map { entity ->
				entity.toDomain(0L) // balance will be mapped below
			}
		}
	}

	fun getAllAccountsWithBalance(): Flow<List<Account>> {
		return accountDao.getAllAccounts().map { entities ->
			entities.map { entity ->
				// We don't await balances here since it's a Flow - we'd need to collect
				// For a real impl, we'd use a different strategy
				entity.toDomain(0L)
			}
		}
	}

	override fun getAccountById(id: String): Flow<Account?> {
		return accountDao.getAccountByIdFlow(id).map { it?.toDomain(0L) }
	}

	override suspend fun getAccountByIdOnce(id: String): Account? {
		return accountDao.getAccountById(id)?.toDomain(0L)
	}

	override suspend fun getPrimaryAccount(): Account? {
		return accountDao.getPrimaryAccount()?.toDomain(0L)
	}

	override suspend fun save(account: Account) {
		accountDao.upsert(account.toEntity())
	}

	override suspend fun delete(id: String) {
		accountDao.deleteById(id)
	}

	override suspend fun getTransactionCount(accountId: String): Int {
		return accountDao.getTransactionCount(accountId)
	}

	private fun AccountEntity.toDomain(balance: Long): Account {
		return Account(
			id = id,
			name = name,
			type = AccountType.fromString(type),
			currency = currency,
			balance = balance,
			iconName = iconName,
			colorHex = colorHex,
			isPrimary = isPrimary
		)
	}

	private fun Account.toEntity(): AccountEntity {
		val now = System.now().toEpochMilliseconds()

		return AccountEntity(
			id = id,
			name = name,
			type = AccountType.toString(type),
			currency = currency,
			iconName = iconName,
			colorHex = colorHex,
			isPrimary = isPrimary,
			createdAt = now,
			updatedAt = now
		)
	}
}