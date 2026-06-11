package dev.apercorn.koin.ui.screens.transactions

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import compose.icons.TablerIcons
import compose.icons.tablericons.*
import dev.apercorn.koin.core.domain.model.TransactionType
import dev.apercorn.koin.core.util.CurrencyFormatter
import dev.apercorn.koin.ui.components.layout.ActionItem
import dev.apercorn.koin.ui.components.layout.ScreenLayout
import dev.apercorn.koin.ui.components.layout.TopBar
import dev.apercorn.koin.ui.components.modal.ModalBottomSheet
import dev.apercorn.koin.ui.screens.transactions.entry.AddTransactionViewModel
import dev.apercorn.koin.ui.screens.transactions.entry.PartyUiModel
import dev.apercorn.koin.ui.screens.transactions.entry.TransactionEntryContent
import dev.apercorn.koin.ui.util.IconProvider
import kotlinx.datetime.*

object TransactionScreen : Screen {

	@Composable
	override fun Content() {
		val viewModel = koinScreenModel<TransactionViewModel>()
		val state by viewModel.state.collectAsState()
		var showAddSheet by remember { mutableStateOf(false) }

		ScreenLayout(
			topBar = {
				TopBar(
					title = "Transactions",
					actions = listOf(
						ActionItem(
							icon = TablerIcons.Plus,
							contentDescription = "Add transaction",
							onClick = { showAddSheet = true }
						),
						ActionItem(
							icon = TablerIcons.Dots,
							contentDescription = "More",
							onClick = { }
						)
					)
				)
			},
			isLoading = state.isLoading
		) {
			LazyColumn(
				verticalArrangement = Arrangement.spacedBy(8.dp),
				modifier = Modifier.fillMaxSize()
			) {
				items(state.transactions) { transaction ->
					TransactionItem(transaction = transaction)
				}

				item {
					Spacer(modifier = Modifier.height(140.dp))
				}
			}
		}

		if (showAddSheet) {
			AddTransactionSheet(onDismiss = { showAddSheet = false })
		}
	}
}

@Composable
private fun TransactionItem(
	transaction: dev.apercorn.koin.core.domain.model.Transaction
) {
	Card(
		modifier = Modifier.fillMaxWidth(),
		colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 16.dp, vertical = 12.dp),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(
				text = transaction.note ?: "${transaction.type.name} · ${transaction.date}",
				style = MaterialTheme.typography.bodyMedium,
				color = MaterialTheme.colorScheme.onSurface
			)
			Text(
				text = CurrencyFormatter.format(transaction.amount, transaction.currency),
				style = MaterialTheme.typography.labelLarge,
				color = when (transaction.type) {
					TransactionType.INCOME -> MaterialTheme.colorScheme.primary
					TransactionType.EXPENSE -> MaterialTheme.colorScheme.error
					TransactionType.TRANSFER -> MaterialTheme.colorScheme.onSurface
				}
			)
		}
	}
}

@Composable
private fun Screen.AddTransactionSheet(onDismiss: () -> Unit) {
	val addVm = koinScreenModel<AddTransactionViewModel>()
	val addState by addVm.state.collectAsState()
	val today = remember { Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date }

	val dateLabel = remember(addState.date) {
		when (addState.date) {
			today -> "Today"
			today.minus(1, DateTimeUnit.DAY) -> "Yesterday"
			today.plus(1, DateTimeUnit.DAY) -> "Tomorrow"
			else -> "${addState.date.dayOfMonth} ${addState.date.month.name.lowercase().replaceFirstChar { it.uppercase() }.take(3)}"
		}
	}

	val fromParty = remember(addState.selectedAccount) {
		val account = addState.selectedAccount
		if (account != null) {
			PartyUiModel(
				id = account.id,
				label = account.name,
				icon = IconProvider.resolve(account.iconName),
				roleTag = "ACCOUNT"
			)
		} else {
			PartyUiModel(
				id = "",
				label = "Select",
				icon = TablerIcons.QuestionMark,
				roleTag = "ACCOUNT"
			)
		}
	}

	val toParty = remember(addState.selectedCategory, addState.transactionType) {
		val roleTag = when (addState.transactionType) {
			TransactionType.EXPENSE -> "EXPENSE"
			TransactionType.INCOME -> "INCOME"
			TransactionType.TRANSFER -> "TRANSFER"
		}
		val category = addState.selectedCategory
		if (category != null) {
			PartyUiModel(
				id = category.id,
				label = category.name,
				icon = IconProvider.resolve(category.iconName),
				roleTag = roleTag
			)
		} else {
			PartyUiModel(
				id = "",
				label = "Select",
				icon = TablerIcons.QuestionMark,
				roleTag = roleTag
			)
		}
	}

	ModalBottomSheet(
		onDismiss = onDismiss,
		showGrabber = true,
		containerColor = MaterialTheme.colorScheme.secondaryContainer
	) {
		TransactionEntryContent(
			dateLabel = dateLabel,
			canGoBack = true,
			canGoForward = addState.date < today,
			fromParty = fromParty,
			toParty = toParty,
			rawExpression = addState.rawExpression,
			currencyCode = addState.currencyCode,
			confirmEnabled = addState.confirmEnabled,
			onPrevDay = addVm::onPrevDay,
			onNextDay = addVm::onNextDay,
			onPickerOpen = addVm::onPickerOpen,
			onFromClick = { /* TODO: open account picker */ },
			onToClick = { /* TODO: open category picker */ },
			onArrowClick = { /* TODO: toggle transaction type */ },
			onAdjustClick = addVm::onAdjustClick,
			onKey = addVm::onKey,
			onConfirm = addVm::onConfirm
		)
	}
}
