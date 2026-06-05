package dev.apercorn.koin.core.domain.model

data class Account(
	/** Unique identifier for the account */
	val id: String,
	/** The name of the account */
	val name: String,
	/** Optional description or notes about the account */
	val description: String? = null,
	/** Type of account (CASH, CHECKING, SAVINGS) */
	val type: AccountType,
	/** ISO currency code for the account */
	val currency: String,
	/** Current balance in cents/smallest currency unit */
	val balance: Long = 0, // computed, not stored
	/** Optional icon name for UI display */
	val iconName: String,
	/** Optional hex color code for customization */
	val colorHex: String,
	/** Whether this is the user's primary/default account */
	val isPrimary: Boolean = false
)

enum class AccountType {
	CASH,
	CHECKING,
	SAVINGS;

	companion object {
		fun fromString(value: String): AccountType = when (value.uppercase()) {
			"CASH" -> CASH
			"CHECKING" -> CHECKING
			"SAVINGS" -> SAVINGS
			else -> CHECKING
		}

		fun toString(type: AccountType): String = type.name
	}
}