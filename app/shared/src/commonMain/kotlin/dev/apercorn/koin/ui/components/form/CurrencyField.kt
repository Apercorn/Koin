package dev.apercorn.koin.ui.components.form

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CurrencyField(
	label: String,
	value: String,
	onValueChange: (String) -> Unit,
	currencySymbol: String,
	placeholder: String = "0.00",
	modifier: Modifier = Modifier
) {
	Column(modifier = modifier.fillMaxWidth()) {
		Text(
			text = label,
			style = MaterialTheme.typography.labelMedium,
			color = MaterialTheme.colorScheme.onPrimaryContainer
		)

		Spacer(modifier = Modifier.height(10.dp))

		Box(
			modifier = Modifier
				.fillMaxWidth()
				.background(
					color = MaterialTheme.colorScheme.secondaryContainer,
					shape = RoundedCornerShape(12.dp)
				)
				.border(
					BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
					RoundedCornerShape(12.dp)
				)
				.padding(horizontal = 16.dp, vertical = 12.dp)
		) {
			Row(
				modifier = Modifier.fillMaxWidth(),
				verticalAlignment = Alignment.CenterVertically
			) {
				Text(
					text = currencySymbol,
					style = MaterialTheme.typography.bodyLarge.copy(
						fontFamily = FontFamily.Monospace
					),
					color = MaterialTheme.colorScheme.onSurface,
					modifier = Modifier.padding(end = 8.dp)
				)

				BasicTextField(
					value = value,
					onValueChange = { newValue ->
						if (newValue.isEmpty() || newValue.matches(Regex("^\\d*\\.?\\d*$"))) {
							onValueChange(newValue)
						}
					},
					modifier = Modifier.weight(1f),
					singleLine = true,
					textStyle = MaterialTheme.typography.bodyLarge.copy(
						color = MaterialTheme.colorScheme.onSurface,
						fontFamily = FontFamily.Monospace
					),
					keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
					cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
					decorationBox = { innerTextField ->
						Box {
							if (value.isEmpty()) {
								Text(
									text = placeholder,
									style = MaterialTheme.typography.bodyLarge.copy(
										fontFamily = FontFamily.Monospace
									),
									color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
								)
							}
							innerTextField()
						}
					}
				)
			}
		}
	}
}