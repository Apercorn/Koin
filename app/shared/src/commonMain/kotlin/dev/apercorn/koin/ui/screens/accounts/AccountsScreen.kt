package dev.apercorn.koin.ui.screens.accounts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import compose.icons.TablerIcons
import compose.icons.tablericons.Dots
import compose.icons.tablericons.Plus
import dev.apercorn.koin.core.util.CurrencyFormatter
import dev.apercorn.koin.ui.components.ScreenLayout
import dev.apercorn.koin.ui.components.TopBar
import dev.apercorn.koin.ui.theme.KoinColors

object AccountsScreen : Screen {

	@Composable
	override fun Content() {
		val viewModel = getScreenModel<AccountsViewModel>()
		val state by viewModel.state.collectAsState()
		var showAddDialog by remember { mutableStateOf(false) }

		ScreenLayout(
			topBar = {
				TopBar(
					title = "Accounts",
					actions = listOf(
						{
							IconButton(onClick = { showAddDialog = true }) {
								Icon(
									imageVector = TablerIcons.Plus,
									contentDescription = "Add account"
								)
							}

							IconButton(onClick = { showAddDialog = true }) {
								Icon(
									imageVector = TablerIcons.Dots,
									contentDescription = "More"
								)
							}
						}
					)
				)
			},
			isLoading = state.isLoading
		) {
			TotalBalanceCard(
				totalBalance = state.totalBalance,
				currency = state.currency
			)

			Spacer(modifier = Modifier.height(12.dp))

			LazyColumn(
				contentPadding = PaddingValues(vertical = 16.dp),
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
private fun TotalBalanceCard(
	totalBalance: Long,
	currency: String
) {
	Box(
		modifier = Modifier
			.fillMaxWidth()
			.padding(20.dp)
	) {
		Column {
			Row(
				horizontalArrangement = Arrangement.SpaceBetween,
				verticalAlignment = Alignment.CenterVertically
			) {
				Text(
					text = "Total Balance",
					style = MaterialTheme.typography.labelLarge,
					color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
				)
				Text(
					text = "All accounts",
					style = MaterialTheme.typography.bodySmall,
					color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.5f)
				)
			}
			Spacer(modifier = Modifier.height(8.dp))
			Row(
				horizontalArrangement = Arrangement.SpaceBetween,
				verticalAlignment = Alignment.Bottom
			) {
				Text(
					text = CurrencyFormatter.format(totalBalance, currency),
					style = MaterialTheme.typography.headlineMedium,
					fontWeight = FontWeight.Bold,
					color = MaterialTheme.colorScheme.onPrimaryContainer
				)
				Text(
					text = if (totalBalance >= 0) "+" else "",
					style = MaterialTheme.typography.bodyMedium,
					color = if (totalBalance >= 0) KoinColors.Income else KoinColors.Expense
				)
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