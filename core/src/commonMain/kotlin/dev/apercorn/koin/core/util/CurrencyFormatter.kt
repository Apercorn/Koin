package dev.apercorn.koin.core.util

import kotlin.math.abs

/**
 * Formats monetary amounts for display.
 *
 * Uses the embedded [CurrencyInfo] registry for symbol and decimal-digit lookup.
 * All amounts are stored as **cents** (smallest currency unit with 2 decimals),
 * meaning "100" in DB displays as "$1.00".
 *
 * For currencies with 0 decimal digits (JPY, KRW, etc.) amounts are whole units
 * and display without a decimal separator.
 */
object CurrencyFormatter {

	/**
	 * Formats a cent-based amount with its currency symbol.
	 *
	 * Examples:
	 * ```
	 * format(100, "USD")   → "$1.00"
	 * format(-50, "USD")   → "-$0.50"
	 * format(500, "JPY")   → "¥500"       (0 decimal digits)
	 * format(1234, "KWD")  → "KD1.234"    (3 decimal digits)
	 * ```
	 *
	 * @param amount Value in cents (or whole units if currency uses 0 decimals).
	 * @param currency ISO 4217 code.
	 * @return Formatted string with symbol.
	 */
	fun format(amount: Long, currency: String): String {
		val info = CurrencyInfo.findOrDefault(currency)
		val sign = if (amount < 0) "-" else ""
		val absAmount = abs(amount)

		return if (info.decimalDigits == 0) {
			// Whole-unit currencies (JPY, KRW, etc.)
			"$sign${info.symbol}$absAmount"
		} else {
			// Cents-based: divide into whole + fractional parts
			val divisor = when (info.decimalDigits) {
				3 -> 1000L
				else -> 100L
			}
			val whole = absAmount / divisor
			val fraction = (absAmount % divisor).toString().padStart(info.decimalDigits, '0')
			"$sign${info.symbol}$whole.$fraction"
		}
	}

	/**
	 * Compact representation for large amounts (K/M suffixes).
	 *
	 * Examples:
	 * ```
	 * formatCompact(150_000, "USD")  → "$1.50K"
	 * formatCompact(2_000_000, "USD") → "$20.00M"
	 * formatCompact(50, "USD")       → "$0.50"
	 * ```
	 *
	 * @param amount Value in cents.
	 * @param currency ISO 4217 code (case-insensitive).
	 * @return Compact formatted string.
	 */
	fun formatCompact(amount: Long, currency: String): String {
		val absAmount = abs(amount) / 100.0
		val symbol = CurrencyInfo.findOrDefault(currency).symbol
		val sign = if (amount < 0) "-" else ""
		return when {
			absAmount >= 1_000_000 -> "$sign$symbol${twoDecimals(absAmount / 1_000_000.0)}M"
			absAmount >= 1_000 -> "$sign$symbol${twoDecimals(absAmount / 1_000.0)}K"
			else -> "$sign$symbol${twoDecimals(absAmount)}"
		}
	}

	/**
	 * Returns only the currency symbol for a given code.
	 *
	 * @param currency ISO 4217 code (case-insensitive).
	 * @return Symbol string (e.g., "$", "€", "¥").
	 */
	fun symbol(currency: String): String =
		CurrencyInfo.findOrDefault(currency).symbol

	/**
	 * Formats a value with exactly 2 decimal places, no grouping.
	 */
	private fun twoDecimals(value: Double): String {
		val whole = value.toLong()
		val fraction = ((value - whole) * 100 + 0.5).toInt().coerceIn(0, 99)
		return "$whole.${fraction.toString().padStart(2, '0')}"
	}
}