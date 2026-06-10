package dev.apercorn.koin.core.domain.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

/**
 * Sealed interface for transactions.
 * OneOff = manual transaction. Recurring = generated from recurring schedule.
 */
@Serializable
sealed interface Transaction {
	/** Unique identifier for the transaction */
	val id: String

	/** ID of the account this transaction belongs to */
	val accountId: String

	/** ID of the category */
	val categoryId: String?

	/** ID of the counterparty */
	val counterpartyId: String?

	/** Transaction amount in cents/smallest currency unit (positive for income, negative for expense) */
	val amount: Long

	/** ISO currency code for the transaction */
	val currency: String

	/** Type of transaction (INCOME, EXPENSE, TRANSFER) */
	val type: TransactionType

	/** Date when the transaction occurred */
	val date: LocalDate

	/** Optional note or description for the transaction */
	val note: String?

	@Serializable
	data class OneOff(
		override val id: String,
		override val accountId: String,
		override val categoryId: String? = null,
		override val counterpartyId: String? = null,
		override val amount: Long,
		override val currency: String,
		override val type: TransactionType,
		override val date: LocalDate,
		override val note: String? = null
	) : Transaction

	@Serializable
	data class Recurring(
		override val id: String,
		override val accountId: String,
		override val categoryId: String? = null,
		override val counterpartyId: String? = null,
		override val amount: Long,
		override val currency: String,
		override val type: TransactionType,
		override val date: LocalDate,
		override val note: String? = null,
		/** ID of the recurring transaction that generated this transaction */
		val recurringId: String
	) : Transaction
}

enum class TransactionType {
	INCOME,
	EXPENSE,
	TRANSFER;

	companion object {
		fun fromString(value: String): TransactionType = when (value.uppercase()) {
			"INCOME" -> INCOME
			"EXPENSE" -> EXPENSE
			"TRANSFER" -> TRANSFER
			else -> EXPENSE
		}

		fun toString(type: TransactionType): String = type.name
	}
}