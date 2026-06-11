package dev.apercorn.koin.ui.screens.transactions.entry

import androidx.compose.runtime.*
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import compose.icons.TablerIcons
import compose.icons.tablericons.QuestionMark
import dev.apercorn.koin.core.domain.model.Account
import dev.apercorn.koin.core.domain.model.Category
import dev.apercorn.koin.core.domain.model.TransactionType
import dev.apercorn.koin.core.util.CurrencyFormatter
import dev.apercorn.koin.ui.util.IconProvider
import kotlinx.datetime.*

object AddTransactionScreen : Screen {

	@Composable
	override fun Content() {
		val viewModel = koinScreenModel<AddTransactionViewModel>()
		val state by viewModel.state.collectAsState()
		val today = remember { Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date }

		val dateLabel = remember(state.date) {
			when (state.date) {
				today -> "Today"
				today.minus(1, DateTimeUnit.DAY) -> "Yesterday"
				today.plus(1, DateTimeUnit.DAY) -> "Tomorrow"
				else -> "${state.date.dayOfMonth} ${state.date.month.name.lowercase().replaceFirstChar { it.uppercase() }.take(3)}"
			}
		}

		val fromParty = remember(state.selectedAccount) {
			val account = state.selectedAccount
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

		val toParty = remember(state.selectedCategory, state.transactionType) {
			val roleTag = when (state.transactionType) {
				TransactionType.EXPENSE -> "EXPENSE"
				TransactionType.INCOME -> "INCOME"
				TransactionType.TRANSFER -> "TRANSFER"
			}
			val category = state.selectedCategory
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

		TransactionEntryContent(
			dateLabel = dateLabel,
			canGoBack = true,
			canGoForward = state.date < today,
			fromParty = fromParty,
			toParty = toParty,
			rawExpression = state.rawExpression,
			currencyCode = state.currencyCode,
			confirmEnabled = state.confirmEnabled,
			onPrevDay = viewModel::onPrevDay,
			onNextDay = viewModel::onNextDay,
			onPickerOpen = viewModel::onPickerOpen,
			onFromClick = { /* TODO: open account picker */ },
			onToClick = { /* TODO: open category picker */ },
			onArrowClick = { /* TODO: toggle transaction type */ },
			onAdjustClick = viewModel::onAdjustClick,
			onKey = viewModel::onKey,
			onConfirm = viewModel::onConfirm
		)
	}
}
