package dev.apercorn.koin.ui.transactions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import dev.apercorn.koin.core.domain.model.TransactionType
import dev.apercorn.koin.core.domain.model.Account
import dev.apercorn.koin.core.domain.model.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTransactionSheet(
	accounts: List<Account>,
	categories: List<Category>,
	onSave: (accountId: String, amount: Long, type: TransactionType, categoryId: String?, note: String?) -> Unit,
	onDismiss: () -> Unit
) {
	var selectedType by remember { mutableStateOf(TransactionType.EXPENSE) }
	var selectedAccountId by remember { mutableStateOf<String?>(null) }
	var selectedCategoryId by remember { mutableStateOf<String?>(null) }
	var amountText by remember { mutableStateOf("") }
	var note by remember { mutableStateOf("") }

	Column(
		modifier = Modifier
			.fillMaxWidth()
			.verticalScroll(rememberScrollState())
			.padding(16.dp)
	) {
		Text(
			text = "New Transaction",
			style = MaterialTheme.typography.headlineSmall,
			fontWeight = FontWeight.Bold,
			modifier = Modifier.padding(bottom = 16.dp)
		)

		// Type selector
		Row(
			horizontalArrangement = Arrangement.spacedBy(8.dp),
			modifier = Modifier.padding(bottom = 16.dp)
		) {
			TransactionType.entries.forEach { type ->
				FilterChip(
					selected = selectedType == type,
					onClick = { selectedType = type },
					label = { Text(type.name) }
				)
			}
		}

		// Amount input
		OutlinedTextField(
			value = amountText,
			onValueChange = { amountText = it },
			label = { Text("Amount") },
			keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
			prefix = { Text("$") },
			modifier = Modifier.fillMaxWidth(),
			singleLine = true
		)

		Spacer(modifier = Modifier.height(12.dp))

		// Account picker
		if (accounts.isNotEmpty()) {
			val selectedAccount = accounts.find { it.id == selectedAccountId }
			ExposedDropdownMenuBox(
				expanded = false,
				onExpandedChange = { }
			) {
				OutlinedTextField(
					value = selectedAccount?.name ?: "Select account",
					onValueChange = {},
					readOnly = true,
					label = { Text("Account") },
					modifier = Modifier.fillMaxWidth(),
					trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = false) }
				)
				ExposedDropdownMenu(
					expanded = false,
					onDismissRequest = { }
				) {
					accounts.forEach { account ->
						DropdownMenuItem(
							text = { Text(account.name) },
							onClick = { selectedAccountId = account.id }
						)
					}
				}
			}
		}

		Spacer(modifier = Modifier.height(12.dp))

		// Category picker
		if (categories.isNotEmpty()) {
			val availableCategories = if (selectedType == TransactionType.TRANSFER) {
				// Transfers show all categories (no income/expense distinction)
				categories
			} else {
				categories.filter { it.type.name == selectedType.name }
			}
			val selectedCategory = availableCategories.find { it.id == selectedCategoryId }

			ExposedDropdownMenuBox(
				expanded = false,
				onExpandedChange = { }
			) {
				OutlinedTextField(
					value = selectedCategory?.let { "${it.iconName.take(2)} ${it.name}" } ?: "Select category",
					onValueChange = {},
					readOnly = true,
					label = { Text("Category") },
					modifier = Modifier.fillMaxWidth(),
					trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = false) }
				)
			}
		}

		Spacer(modifier = Modifier.height(12.dp))

		// Note
		OutlinedTextField(
			value = note,
			onValueChange = { note = it },
			label = { Text("Note (optional)") },
			modifier = Modifier.fillMaxWidth(),
			maxLines = 3
		)

		Spacer(modifier = Modifier.height(24.dp))

		// Buttons
		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.spacedBy(12.dp)
		) {
			OutlinedButton(
				onClick = onDismiss,
				modifier = Modifier.weight(1f)
			) {
				Text("Cancel")
			}
			Button(
				onClick = {
					val amount = (amountText.toDoubleOrNull() ?: 0.0).let { (it * 100).toLong() }
					if (amount > 0 && selectedAccountId != null) {
						onSave(
							selectedAccountId!!,
							amount,
							selectedType,
							selectedCategoryId,
							note.ifBlank { null }
						)
					}
				},
				modifier = Modifier.weight(1f),
				enabled = amountText.toDoubleOrNull() != null && selectedAccountId != null
			) {
				Text("Save")
			}
		}
	}
}