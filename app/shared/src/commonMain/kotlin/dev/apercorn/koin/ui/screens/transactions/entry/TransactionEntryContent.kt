package dev.apercorn.koin.ui.screens.transactions.entry

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.apercorn.koin.ui.screens.transactions.entry.components.*

@Composable
fun TransactionEntryContent(
	dateLabel: String,
	canGoBack: Boolean,
	canGoForward: Boolean,
	fromParty: PartyUiModel,
	toParty: PartyUiModel,
	rawExpression: String,
	currencyCode: String,
	confirmEnabled: Boolean,
	onPrevDay: () -> Unit,
	onNextDay: () -> Unit,
	onPickerOpen: () -> Unit,
	onFromClick: () -> Unit,
	onToClick: () -> Unit,
	onArrowClick: () -> Unit,
	onAdjustClick: () -> Unit,
	onKey: (NumpadKey) -> Unit,
	onConfirm: () -> Unit,
	modifier: Modifier = Modifier
) {
	Column(
		modifier = modifier.padding(horizontal = 16.dp)
	) {
		// date picker
		TransactionDatePicker(
			selectedDateLabel = dateLabel,
			canGoBack = canGoBack,
			canGoForward = canGoForward,
			onPrevDay = onPrevDay,
			onNextDay = onNextDay,
			onPickerOpen = onPickerOpen
		)

		Spacer(modifier = Modifier.height(16.dp))

		// from → to party row
		TransactionPartyRow(
			fromParty = fromParty,
			toParty = toParty,
			onFromClick = onFromClick,
			onToClick = onToClick,
			onArrowClick = onArrowClick
		)

		Spacer(modifier = Modifier.height(24.dp))

		// amount display
		AmountDisplay(
			rawExpression = rawExpression,
			onAdjustClick = onAdjustClick
		)

		Spacer(modifier = Modifier.height(16.dp))

		// numpad
		TransactionNumpad(
			currencyCode = currencyCode,
			confirmEnabled = confirmEnabled,
			onKey = onKey,
			onConfirm = onConfirm
		)
	}
}
