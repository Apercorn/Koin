package dev.apercorn.koin.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.navigator.tab.TabOptions
import dev.apercorn.koin.ui.accounts.AccountsScreen
import dev.apercorn.koin.ui.overview.OverviewScreen
import dev.apercorn.koin.ui.search.SearchScreen
import dev.apercorn.koin.ui.settings.SettingsScreen
import dev.apercorn.koin.ui.transactions.TransactionScreen
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import koin.app.shared.generated.resources.*

private val PillHeight = 55.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigator() {
	val haptic = LocalHapticFeedback.current
	TabNavigator(OverviewTab) {
		val tabNavigator = LocalTabNavigator.current

		Scaffold(
			modifier = Modifier.fillMaxSize(),
			bottomBar = {
				Box(
					modifier = Modifier
						.fillMaxWidth()
						.padding(horizontal = 16.dp, vertical = 32.dp)
				) {
					Row(
						modifier = Modifier.fillMaxWidth(),
						verticalAlignment = Alignment.CenterVertically,
						horizontalArrangement = Arrangement.spacedBy(8.dp)
					) {
						// Main navigation pill
						FloatingBottomBar(
							modifier = Modifier.weight(1f)
						)

						// Search button
						DetachedSearchButton(
							onClick = {
								haptic.performHapticFeedback(HapticFeedbackType.LongPress)
								tabNavigator.current = SearchTab
							}
						)
					}
				}
			}
		) { paddingValues ->
			Box(
				modifier = Modifier
					.fillMaxSize()
					.padding(paddingValues)
			) {
				CurrentTab() // Renders the content of the selected tab
			}
		}
	}
}

/**
 * Navigation pill
 */
@Composable
private fun FloatingBottomBar(
	modifier: Modifier = Modifier
) {
	Surface(
		modifier = modifier
			.height(PillHeight)
			.clip(RoundedCornerShape(50)),
		color = MaterialTheme.colorScheme.surface,
		tonalElevation = 0.dp,
		shadowElevation = 12.dp
	) {
		Row(
			modifier = Modifier
				.fillMaxSize()
				.padding(horizontal = 0.dp),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceEvenly
		) {
			FloatingTabItem(
				tab = OverviewTab,
				iconRes = Res.drawable.ic_home,
				iconResSelected = Res.drawable.ic_home_filled,
				label = "Overview"
			)
			FloatingTabItem(
				tab = TransactionsTab,
				iconRes = Res.drawable.ic_currency,
				iconResSelected = Res.drawable.ic_currency_filled,
				label = "Transactions"
			)
			FloatingTabItem(
				tab = AccountsTab,
				iconRes = Res.drawable.ic_bank,
				iconResSelected = Res.drawable.ic_bank_filled,
				label = "Accounts"
			)
			FloatingTabItem(
				tab = SettingsTab,
				iconRes = Res.drawable.ic_settings,
				iconResSelected = Res.drawable.ic_settings_filled,
				label = "Settings"
			)
		}
	}
}

/**
 * Individual tab item.
 */
@Composable
private fun RowScope.FloatingTabItem(
	tab: Tab,
	iconRes: DrawableResource,
	iconResSelected: DrawableResource,
	label: String
) {
	val tabNavigator = LocalTabNavigator.current
	val selected = tabNavigator.current == tab
	val haptic = LocalHapticFeedback.current
	var pressed by remember { mutableStateOf(false) }

	val scale by animateFloatAsState(
		targetValue = if (pressed) 0.92f else 1f,
		label = "tabScale"
	)

	val icon = if (selected) iconResSelected else iconRes

	Surface(
		modifier = Modifier
			.padding(vertical = 5.dp, horizontal = 2.dp)
			.scale(scale),
		onClick = {
			haptic.performHapticFeedback(HapticFeedbackType.LongPress)
			tabNavigator.current = tab
		},
		shape = RoundedCornerShape(50),
		color = if (selected) MaterialTheme.colorScheme.surfaceVariant else Color.Transparent,
		contentColor = if (selected) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
	) {
		Row(
			modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			Icon(
				painter = painterResource(icon),
				contentDescription = label,
				modifier = Modifier.size(24.dp)
			)
			AnimatedVisibility(
				visible = selected,
				enter = fadeIn(animationSpec = tween(120)) +
					expandHorizontally(animationSpec = tween(150), expandFrom = Alignment.Start),
				exit = fadeOut(animationSpec = tween(80)) +
					shrinkHorizontally(animationSpec = tween(120), shrinkTowards = Alignment.Start)
			) {
				Text(
					text = label,
					style = MaterialTheme.typography.labelMedium,
					fontWeight = FontWeight.SemiBold,
					maxLines = 1,
					overflow = TextOverflow.Ellipsis,
					modifier = Modifier.padding(start = 8.dp)
				)
			}
		}
	}
}

@Composable
private fun DetachedSearchButton(
	onClick: () -> Unit,
	modifier: Modifier = Modifier
) {
	var pressed by remember { mutableStateOf(false) }

	val scale by animateFloatAsState(
		targetValue = if (pressed) 0.92f else 1f,
		label = "searchScale"
	)

	Surface(
		modifier = modifier
			.size(PillHeight)
			.scale(scale),
		onClick = onClick,
		shape = CircleShape,
		color = MaterialTheme.colorScheme.surface,
		contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
		tonalElevation = 0.dp,
		shadowElevation = 12.dp
	) {
		Box(contentAlignment = Alignment.Center) {
			Icon(
				painter = painterResource(Res.drawable.ic_search),
				contentDescription = "Search",
				modifier = Modifier.size(28.dp)
			)
		}
	}
}

private object OverviewTab : Tab {
	override val options: TabOptions
		@Composable
		get() = TabOptions(
			index = 0u.toUShort(),
			title = "Overview"
		)

	@Composable
	override fun Content() {
		OverviewScreen.Content()
	}
}

private object TransactionsTab : Tab {
	override val options: TabOptions
		@Composable
		get() = TabOptions(
			index = 1u.toUShort(),
			title = "Transactions"
		)

	@Composable
	override fun Content() {
		TransactionScreen.Content()
	}
}

private object AccountsTab : Tab {
	override val options: TabOptions
		@Composable
		get() = TabOptions(
			index = 2u.toUShort(),
			title = "Accounts"
		)

	@Composable
	override fun Content() {
		AccountsScreen.Content()
	}
}

private object SettingsTab : Tab {
	override val options: TabOptions
		@Composable
		get() = TabOptions(
			index = 3u.toUShort(),
			title = "Settings"
		)

	@Composable
	override fun Content() {
		SettingsScreen.Content()
	}
}

private object SearchTab : Tab {
	override val options: TabOptions
		@Composable
		get() = TabOptions(
			index = 4u.toUShort(),
			title = "Search"
		)

	@Composable
	override fun Content() {
		SearchScreen.Content()
	}
}
