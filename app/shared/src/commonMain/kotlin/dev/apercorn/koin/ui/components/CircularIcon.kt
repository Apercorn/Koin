package dev.apercorn.koin.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularIcon(
	imageVector: ImageVector,
	onClick: () -> Unit,
	modifier: Modifier = Modifier,
	iconSize: Dp = 22.dp,
	circleSize: Dp = 40.dp,
	backgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer,
	tint: Color = MaterialTheme.colorScheme.onSurface,
	contentDescription: String? = null
) {
	Surface(
		onClick = onClick,
		modifier = modifier.size(circleSize),
		shape = CircleShape,
		color = backgroundColor,
		contentColor = tint
	) {
		Box(
			contentAlignment = Alignment.Center
		) {
			Icon(
				imageVector = imageVector,
				contentDescription = contentDescription,
				tint = tint, // Explicit tint overrides contentColor if they differ
				modifier = Modifier.size(iconSize)
			)
		}
	}
}

@Composable
fun ColorIcon(
	imageVector: ImageVector,
	onClick: () -> Unit,
	modifier: Modifier = Modifier,
	size: Dp = 40.dp,
	color: Color = MaterialTheme.colorScheme.primary,
	contentDescription: String? = null
) {
	CircularIcon(
		imageVector = imageVector,
		onClick = onClick,
		modifier = modifier,
		iconSize = size * 0.6f,
		circleSize = size,
		backgroundColor = color.copy(alpha = 0.15f),
		tint = color,
		contentDescription = contentDescription
	)
}