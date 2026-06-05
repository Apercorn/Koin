package dev.apercorn.koin.core.domain.model

data class Counterparty(
	/** Unique identifier for the counterparty */
	val id: String,
	/** Name of the person or merchant */
	val name: String,
	/** Optional email address for contact */
	val email: String? = null,
	/** Optional phone number for contact */
	val phone: String? = null,
	/** Optional notes or additional information about the counterparty */
	val notes: String? = null
)

data class Money(
	val amount: Long,
	val currency: String
) {
	companion object {
		fun zero(currency: String) = Money(0, currency)
	}
}