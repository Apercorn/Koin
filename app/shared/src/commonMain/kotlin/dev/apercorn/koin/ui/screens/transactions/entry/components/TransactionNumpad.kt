package dev.apercorn.koin.ui.screens.transactions.entry.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.TablerIcons
import compose.icons.tablericons.*
import dev.apercorn.koin.ui.screens.transactions.entry.NumpadKey
import dev.apercorn.koin.ui.screens.transactions.entry.Op

@Composable
fun TransactionNumpad(
	currencyCode: String,
	confirmEnabled: Boolean,
	onKey: (NumpadKey) -> Unit,
	onConfirm: () -> Unit,
	modifier: Modifier = Modifier
) {
	Column(
		modifier = modifier
			.fillMaxWidth()
			.padding(horizontal = 8.dp),
		verticalArrangement = Arrangement.spacedBy(8.dp)
	) {
		// Row 1: ÷  7  8  9  ⌫
		NumpadRow(
			keys = listOf(
				NumpadKey.Operator(Op.DIVIDE),
				NumpadKey.Digit(7),
				NumpadKey.Digit(8),
				NumpadKey.Digit(9),
				NumpadKey.Backspace
			),
			onKey = onKey
		)

		// Row 2: ×  4  5  6  .
		NumpadRow(
			keys = listOf(
				NumpadKey.Operator(Op.MULTIPLY),
				NumpadKey.Digit(4),
				NumpadKey.Digit(5),
				NumpadKey.Digit(6),
				NumpadKey.Decimal
			),
			onKey = onKey
		)

		// Row 3: −  1  2  3  ✓
		NumpadRow(
			keys = listOf(
				NumpadKey.Operator(Op.SUBTRACT),
				NumpadKey.Digit(1),
				NumpadKey.Digit(2),
				NumpadKey.Digit(3),
				NumpadKey.Confirm
			),
			onKey = onKey,
			confirmEnabled = confirmEnabled,
			onConfirm = onConfirm
		)

		// Row 4: +  [CUR]  0  .  (confirm slot reserved)
		NumpadRow(
			keys = listOf(
				NumpadKey.Operator(Op.ADD),
				NumpadKey.CurrencyToggle,
				NumpadKey.Digit(0),
				NumpadKey.Decimal,
				NumpadKey.Confirm
			),
			onKey = onKey,
			currencyCode = currencyCode,
			confirmEnabled = confirmEnabled,
			onConfirm = onConfirm
		)
	}
}

@Composable
private fun NumpadRow(
	keys: List<NumpadKey>,
	onKey: (NumpadKey) -> Unit,
	currencyCode: String? = null,
	confirmEnabled: Boolean = true,
	onConfirm: (() -> Unit)? = null
) {
	Row(
		modifier = Modifier.fillMaxWidth(),
		horizontalArrangement = Arrangement.spacedBy(8.dp)
	) {
		keys.forEach { key ->
			val label = when (key) {
				is NumpadKey.Digit -> key.value.toString()
				is NumpadKey.Decimal -> "."
				is NumpadKey.Operator -> when (key.op) {
					Op.DIVIDE -> "÷"
					Op.MULTIPLY -> "×"
					Op.SUBTRACT -> "−"
					Op.ADD -> "+"
				}
				is NumpadKey.Backspace -> "⌫"
				is NumpadKey.CurrencyToggle -> currencyCode ?: "USD"
				is NumpadKey.Confirm -> "✓"
			}

			val isOperator = key is NumpadKey.Operator
			val isConfirm = key is NumpadKey.Confirm

			NumpadButton(
				label = label,
				onClick = {
					if (isConfirm && onConfirm != null) {
						if (confirmEnabled) onConfirm()
					} else {
						onKey(key)
					}
				},
				modifier = Modifier.weight(1f),
				isAccent = isConfirm,
				isSecondary = isOperator || key is NumpadKey.Backspace || key is NumpadKey.CurrencyToggle,
				enabled = if (isConfirm) confirmEnabled else true
			)
		}
	}
}

@Composable
private fun NumpadButton(
	label: String,
	onClick: () -> Unit,
	modifier: Modifier = Modifier,
	isAccent: Boolean = false,
	isSecondary: Boolean = false,
	enabled: Boolean = true
) {
	val containerColor = when {
		isAccent -> MaterialTheme.colorScheme.primary
		isSecondary -> MaterialTheme.colorScheme.surface
		else -> MaterialTheme.colorScheme.surfaceVariant
	}
	val contentColor = when {
		isAccent -> MaterialTheme.colorScheme.onPrimary
		isSecondary -> MaterialTheme.colorScheme.onSurfaceVariant
		else -> MaterialTheme.colorScheme.onSurface
	}

	Surface(
		onClick = onClick,
		modifier = modifier.height(56.dp),
		shape = RoundedCornerShape(12.dp),
		color = containerColor,
		enabled = enabled
	) {
		Box(contentAlignment = Alignment.Center) {
			Text(
				text = label,
				style = MaterialTheme.typography.titleMedium.copy(
					fontWeight = if (isAccent) FontWeight.Bold else FontWeight.Medium,
					fontSize = if (label.length > 2) 14.sp else 20.sp
				),
				color = if (enabled) contentColor else contentColor.copy(alpha = 0.4f)
			)
		}
	}
}
