package dev.apercorn.koin.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun AmountKeypad(
	value: String,
	onValueChange: (String) -> Unit,
	modifier: Modifier = Modifier
) {
	OutlinedTextField(
		value = value,
		onValueChange = { newValue ->
			// Only allow digits and up to one decimal point
			if (newValue.isEmpty() || newValue.matches(Regex("^\\d*\\.?\\d{0,2}$"))) {
				onValueChange(newValue)
			}
		},
		label = { Text("Amount") },
		keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
		prefix = { Text("$", fontWeight = FontWeight.Bold) },
		modifier = modifier.fillMaxWidth(),
		singleLine = true
	)
}

// Expression evaluation helper for calculator keypad
fun evaluateExpression(expression: String): Double? {
	return try {
		// Simple expression evaluation (e.g., "105307.89 / 3")
		val sanitized = expression.replace(",", "").replace(" ", "")
		if (sanitized.matches(Regex("^\\d+(\\.\\d+)?([+\\-*/]\\d+(\\.\\d+)?)*$"))) {
			// Use a simple recursive parser
			parseSimpleExpression(sanitized)
		} else {
			sanitized.toDoubleOrNull()
		}
	} catch (e: Exception) {
		null
	}
}

private fun parseSimpleExpression(expr: String): Double {
	// Very simple left-to-right parser for +, -, *, /
	val parts = mutableListOf<String>()
	val ops = mutableListOf<Char>()
	var current = StringBuilder()

	for (ch in expr) {
		when (ch) {
			'+', '-', '*', '/' -> {
				if (current.isNotEmpty()) {
					parts.add(current.toString())
					current = StringBuilder()
				}
				ops.add(ch)
			}

			else -> current.append(ch)
		}
	}
	if (current.isNotEmpty()) parts.add(current.toString())

	if (parts.size <= 1) return expr.toDouble()

	// First do * and /
	var result = parts[0].toDouble()
	var i = 0
	while (i < ops.size) {
		when (ops[i]) {
			'*' -> result *= parts[i + 1].toDouble()
			'/' -> result /= parts[i + 1].toDouble()
			'+' -> result += parts[i + 1].toDouble()
			'-' -> result -= parts[i + 1].toDouble()
		}
		i++
	}

	return result
}