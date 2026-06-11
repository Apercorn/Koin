package dev.apercorn.koin.ui.screens.accounts.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.apercorn.koin.core.util.CurrencyFormatter
import dev.apercorn.koin.ui.screens.accounts.AccountWithBalance
import dev.apercorn.koin.ui.theme.KoinTheme
import dev.apercorn.koin.ui.util.IconProvider

@Composable
fun AccountCard(
	accountWithBalance: AccountWithBalance,
	onClick: () -> Unit = {}
) {
	val account = accountWithBalance.account
	val icon: ImageVector = IconProvider.resolve(account.iconName)
	val iconColor = IconProvider.parseColor(account.colorHex)
	val typeLabel = account.type.name

	Card(
		onClick = onClick,
		colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
		shape = RoundedCornerShape(18.dp),
		modifier = Modifier.fillMaxWidth()
	) {
		Row(
			modifier = Modifier.fillMaxWidth().padding(16.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			// Row index 0: Circular icon
			Box(
				modifier = Modifier.size(55.dp)
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

			// Row index 1: Name + subtitle
			Column(
				modifier = Modifier.weight(1f),
				verticalArrangement = Arrangement.Center
			) {
				// Account name
				Text(
					text = account.name,
					style = MaterialTheme.typography.labelMedium,
					color = MaterialTheme.colorScheme.onSurface,
				)
				Spacer(modifier = Modifier.height(2.dp))
				Row(verticalAlignment = Alignment.CenterVertically) {
					// Account type
					Text(
						text = typeLabel,
						style = MaterialTheme.typography.bodyMedium,
						color = MaterialTheme.colorScheme.onSurfaceVariant,
					)
					Box(
						modifier = Modifier.padding(horizontal = 6.dp).size(4.dp)
							.background(color = MaterialTheme.colorScheme.onSurfaceVariant, shape = CircleShape)
					)
					// Account currency
					Text(
						text = account.currency,
						style = MaterialTheme.typography.bodyMedium,
						color = MaterialTheme.colorScheme.onSurfaceVariant,
					)
				}
			}

			// Row index 2: Balance
			Column(
				horizontalAlignment = Alignment.End,
				verticalArrangement = Arrangement.Center,
				modifier = Modifier.padding(end = 5.dp)
			) {
				Text(
					text = CurrencyFormatter.format(accountWithBalance.balance, account.currency),
					style = MaterialTheme.typography.displaySmall,
					color = MaterialTheme.colorScheme.onSurface,
				)
				Spacer(modifier = Modifier.height(1.dp))
				Text(
					text = "+$0.00 (0.00%)",
					style = MaterialTheme.typography.displaySmall.copy(fontSize = 12.sp),
					color = KoinTheme.colors.profit
				)
			}
		}
	}
}
