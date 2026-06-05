package dev.apercorn.koin.ui.accounts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import compose.icons.TablerIcons
import compose.icons.tablericons.Plus
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import dev.apercorn.koin.core.util.CurrencyFormatter
import dev.apercorn.koin.ui.components.KoinTopBar
import dev.apercorn.koin.ui.theme.KoinColors

object AccountsScreen : Screen {

	@Composable
	override fun Content() {
		val viewModel = getScreenModel<AccountsViewModel>()
		val state by viewModel.state.collectAsState()
		var showAddDialog by remember { mutableStateOf(false) }

		Column(modifier = Modifier.fillMaxSize()) {
			KoinTopBar(
				title = "Accounts",
				actions = listOf(
					{
						IconButton(onClick = { showAddDialog = true }) {
							Icon(
								imageVector = TablerIcons.Plus,
								contentDescription = "Add account"
							)
						}
					}
				)
			)

			Spacer(modifier = Modifier.height(16.dp))

			if (state.isLoading) {
				Box(
					modifier = Modifier.fillMaxSize(),
					contentAlignment = Alignment.Center
				) {
					CircularProgressIndicator()
				}
			} else if (state.accounts.isEmpty()) {
				Box(
					modifier = Modifier.fillMaxSize(),
					contentAlignment = Alignment.Center
				) {
					Column(horizontalAlignment = Alignment.CenterHorizontally) {
						Text(
							text = "No accounts yet",
							color = MaterialTheme.colorScheme.onSurfaceVariant
						)
						Spacer(modifier = Modifier.height(8.dp))
						Button(onClick = { showAddDialog = true }) {
							Text("Add Account")
						}
					}
				}
			} else {
				LazyColumn(
					contentPadding = PaddingValues(16.dp),
					verticalArrangement = Arrangement.spacedBy(12.dp)
				) {
					items(state.accounts) { accountWithBalance ->
						AccountCard(
							name = accountWithBalance.account.name,
							type = accountWithBalance.account.type.name,
							balance = accountWithBalance.balance,
							currency = accountWithBalance.account.currency,
							onDelete = { viewModel.deleteAccount(accountWithBalance.account.id) }
						)
					}
				}
			}
		}

		if (showAddDialog) {
			AddAccountDialog(
				onDismiss = { showAddDialog = false },
				onSave = { name, type ->
					viewModel.addAccount(name, type)
					showAddDialog = false
				}
			)
		}
	}
}

@Composable
private fun AccountCard(
	name: String,
	type: String,
	balance: Long,
	currency: String,
	onDelete: () -> Unit
) {
	Card(modifier = Modifier.fillMaxWidth()) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(16.dp),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			Column {
				Text(
					text = name,
					style = MaterialTheme.typography.titleMedium,
					fontWeight = FontWeight.Medium
				)
				Text(
					text = type,
					style = MaterialTheme.typography.bodySmall,
					color = MaterialTheme.colorScheme.onSurfaceVariant
				)
			}
			Row(verticalAlignment = Alignment.CenterVertically) {
				Text(
					text = CurrencyFormatter.format(balance, currency),
					style = MaterialTheme.typography.titleMedium,
					fontWeight = FontWeight.SemiBold,
					color = if (balance >= 0) KoinColors.Income else KoinColors.Expense
				)
				Spacer(modifier = Modifier.width(8.dp))
				TextButton(onClick = onDelete) {
					Text("Delete", color = MaterialTheme.colorScheme.error)
				}
			}
		}
	}
}

@Composable
private fun AddAccountDialog(
	onDismiss: () -> Unit,
	onSave: (name: String, type: dev.apercorn.koin.core.domain.model.AccountType) -> Unit
) {
	var name by remember { mutableStateOf("") }
	var selectedType by remember { mutableStateOf(dev.apercorn.koin.core.domain.model.AccountType.CHECKING) }

	AlertDialog(
		onDismissRequest = onDismiss,
		title = { Text("Add Account") },
		text = {
			Column {
				OutlinedTextField(
					value = name,
					onValueChange = { name = it },
					label = { Text("Account name") },
					singleLine = true,
					modifier = Modifier.fillMaxWidth()
				)
				Spacer(modifier = Modifier.height(12.dp))
				Text("Account type", style = MaterialTheme.typography.bodyMedium)
				Spacer(modifier = Modifier.height(8.dp))
				Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
					dev.apercorn.koin.core.domain.model.AccountType.entries.forEach { type ->
						FilterChip(
							selected = selectedType == type,
							onClick = { selectedType = type },
							label = { Text(type.name) }
						)
					}
				}
			}
		},
		confirmButton = {
			Button(
				onClick = { onSave(name, selectedType) },
				enabled = name.isNotBlank()
			) {
				Text("Save")
			}
		},
		dismissButton = {
			TextButton(onClick = onDismiss) {
				Text("Cancel")
			}
		}
	)
}