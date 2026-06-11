package dev.apercorn.koin.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*

/**
 * Generic button with optional leading and trailing icons.
 */
@Composable
fun Button(
	text: String,
	textStyle: TextStyle = MaterialTheme.typography.labelLarge,
	onClick: () -> Unit,
	modifier: Modifier = Modifier,
	leadingIcon: ImageVector? = null,
	trailingIcon: ImageVector? = null,
	iconSize: Dp = 20.dp,
	enabled: Boolean = true
) {
	Button(
		onClick = onClick,
		modifier = modifier,
		enabled = enabled,
		shape = RoundedCornerShape(12.dp),
		colors = ButtonDefaults.buttonColors(
			containerColor = MaterialTheme.colorScheme.primary,
			contentColor = MaterialTheme.colorScheme.onPrimary
		)
	) {
		ButtonContent(
			text = text,
			textStyle = textStyle,
			leadingIcon = leadingIcon,
			trailingIcon = trailingIcon,
			iconSize = iconSize
		)
	}
}

@Composable
fun SmallPillButton(
	text: String,
	onClick: () -> Unit,
	modifier: Modifier = Modifier,
	leadingIcon: ImageVector? = null,
	enabled: Boolean = true
) {
	TextButton(
		onClick = onClick,
		modifier = modifier.height(25.dp),
		enabled = enabled,
		shape = CircleShape,
		contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
		colors = ButtonDefaults.textButtonColors(
			containerColor = MaterialTheme.colorScheme.secondaryContainer,
			contentColor = MaterialTheme.colorScheme.onSurface
		)
	) {
		ButtonContent(
			text = text,
			textStyle = MaterialTheme.typography.labelSmall.copy(
				fontSize = 10.sp,
				fontWeight = FontWeight.SemiBold
			),
			leadingIcon = leadingIcon,
			trailingIcon = null,
			iconSize = 15.dp
		)
	}
}

/**
 * Shared button content layout with icon + text arrangement.
 */
@Composable
private fun ButtonContent(
	text: String,
	textStyle: TextStyle = MaterialTheme.typography.labelLarge,
	leadingIcon: ImageVector?,
	trailingIcon: ImageVector?,
	iconSize: Dp
) {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Center
	) {
		if (leadingIcon != null) {
			Icon(
				imageVector = leadingIcon,
				contentDescription = null,
				modifier = Modifier.size(iconSize)
			)
			Spacer(modifier = Modifier.width(8.dp))
		}

		Text(
			text = text,
			style = textStyle
		)

		if (trailingIcon != null) {
			Spacer(modifier = Modifier.width(8.dp))
			Icon(
				imageVector = trailingIcon,
				contentDescription = null,
				modifier = Modifier.size(iconSize)
			)
		}
	}
}
