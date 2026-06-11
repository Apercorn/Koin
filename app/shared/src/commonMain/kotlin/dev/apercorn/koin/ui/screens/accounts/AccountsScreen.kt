package dev.apercorn.koin.ui.screens.accounts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import compose.icons.TablerIcons
import compose.icons.tablericons.*
import dev.apercorn.koin.core.util.CurrencyFormatter
import dev.apercorn.koin.ui.components.ScreenLayout
import dev.apercorn.koin.ui.components.TopBar
import dev.apercorn.koin.ui.theme.KoinColors
import dev.apercorn.koin.ui.util.IconProvider

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

							IconButton(onClick = {  }) {
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
			TotalBalance(
				totalBalance = state.totalBalance,
				currency = state.currency
			)

			Spacer(modifier = Modifier.height(30.dp))

			Text(
				text = "Savings Account",
				style = MaterialTheme.typography.labelLarge,
				color = MaterialTheme.colorScheme.onSurface,
				fontWeight = FontWeight.Bold,
			)

			Spacer(modifier = Modifier.height(15.dp))

			LazyColumn(
				// contentPadding = PaddingValues(vertical = 16.dp),
				verticalArrangement = Arrangement.spacedBy(12.dp)
			) {
				items(state.accounts) { accountWithBalance ->
					AccountCard(accountWithBalance = accountWithBalance)
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
	accountWithBalance: AccountWithBalance
) {
	val account = accountWithBalance.account
	val icon: ImageVector = IconProvider.resolve(account.iconName)
	val iconColor = IconProvider.parseColor(account.colorHex)
	val typeLabel = account.type.name

	Card(
		onClick = { },
		colors = CardDefaults.cardColors(containerColor = KoinColors.DarkSurface),
		shape = RoundedCornerShape(18.dp),
		modifier = Modifier
			.fillMaxWidth()
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(16.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			// Left: Circular icon
			Box(
				modifier = Modifier
					.size(55.dp)
					.background(iconColor.copy(alpha = 0.2f), shape = CircleShape),
				contentAlignment = Alignment.Center
			) {
				Icon(
					imageVector = icon,
					contentDescription = account.name,
					tint = iconColor,
					modifier = Modifier.size(33.dp)
				)
			}

			Spacer(modifier = Modifier.width(16.dp))

			// Middle: Name + subtitle
			Column(
				modifier = Modifier.weight(1f),
				verticalArrangement = Arrangement.Center
			) {
				Text(
					text = account.name,
					color = KoinColors.DarkOnSurface,
					fontSize = 16.sp,
					fontWeight = FontWeight.Bold
				)
				Spacer(modifier = Modifier.height(1.dp))
				Row(
					verticalAlignment = Alignment.CenterVertically
				) {
					Text(
						text = typeLabel,
						color = KoinColors.DarkOnSurfaceVariant,
						fontWeight = FontWeight.SemiBold,
						fontSize = 12.sp
					)

					Box(
						modifier = Modifier
							.padding(horizontal = 6.dp)
							.size(4.dp)
							.background(
								color = KoinColors.DarkOnSurfaceVariant,
								shape = CircleShape
							)
					)

					Text(
						text = account.currency,
						color = KoinColors.DarkOnSurfaceVariant,
						fontWeight = FontWeight.SemiBold,
						fontSize = 12.sp
					)
				}
			}

			// Right: Balance
			Column(
				horizontalAlignment = Alignment.End,
				verticalArrangement = Arrangement.Center,
				modifier = Modifier.padding(end = 5.dp)
			) {
				Text(
					text = CurrencyFormatter.format(accountWithBalance.balance, account.currency),
					color = KoinColors.DarkOnSurface,
					fontSize = 16.sp,
					fontWeight = FontWeight.Bold
				)

				Spacer(modifier = Modifier.height(1.dp))

				Text(
					text = "+$6669 (3.87%)",
					color = KoinColors.Green,
					fontSize = 12.sp,
					fontWeight = FontWeight.SemiBold
				)

			}
		}
	}
}

@Composable
private fun TotalBalance(
	totalBalance: Long,
	currency: String
) {
	Box(
		modifier = Modifier
			.fillMaxWidth()
	) {
		Column(
			verticalArrangement = Arrangement.spacedBy(10.dp)
		) {
//			Row(
//				horizontalArrangement = Arrangement.SpaceBetween,
//				verticalAlignment = Alignment.CenterVertically
//			) {
//				Text(
//					text = "TOTAL BALANCE",
//					style = MaterialTheme.typography.labelLarge,
//					color = MaterialTheme.colorScheme.onSurfaceVariant,
//					fontWeight = FontWeight.Bold,
//					fontSize = 13.sp,
//				)
//			}

			Text(
				text = CurrencyFormatter.format(totalBalance, currency),
				style = MaterialTheme.typography.headlineLarge,
				fontWeight = FontWeight.Bold,
				color = MaterialTheme.colorScheme.onSurface
			)

			Row(
				horizontalArrangement = Arrangement.spacedBy(10.dp),
				verticalAlignment = Alignment.CenterVertically,
			) {
				Text(
					text = "+$8067.10 (3.69%)",
					style = MaterialTheme.typography.labelLarge,
					color = KoinColors.Income
				)

				Text(
					text = "last week",
					style = MaterialTheme.typography.labelLarge,
					color = MaterialTheme.colorScheme.onSurfaceVariant
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
	var selectedType by remember { mutableStateOf(dev.apercorn.koin.core.domain.model.AccountType.Checkings) }

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