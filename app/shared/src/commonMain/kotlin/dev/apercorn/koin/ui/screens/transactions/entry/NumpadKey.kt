package dev.apercorn.koin.ui.screens.transactions.entry

/**
 * Arithmetic operator used in the keypad for on-the-fly calculations.
 */
enum class Op {
	DIVIDE,
	MULTIPLY,
	SUBTRACT,
	ADD
}

/**
 * Sealed type representing every possible key press on the transaction keypad.
 * All expression state lives in the ViewModel — the keypad just emits events.
 */
sealed class NumpadKey {
	data class Digit(val value: Int) : NumpadKey()
	data object Decimal : NumpadKey()
	data class Operator(val op: Op) : NumpadKey()
	data object Backspace : NumpadKey()
	data object CurrencyToggle : NumpadKey() // cycles currency or opens picker
	data object Confirm : NumpadKey()       // ✓ — finish entry
}

