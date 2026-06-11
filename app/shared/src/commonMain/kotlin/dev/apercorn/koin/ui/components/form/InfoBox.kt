package dev.apercorn.koin.ui.components.form

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoBox(
	title: String,
	description: String,
	leadingIcon: @Composable (() -> Unit)? = null,
	iconPadding: PaddingValues = PaddingValues(0.dp),
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier
			.fillMaxWidth()
			.background(
				color = MaterialTheme.colorScheme.secondaryContainer,
				shape = RoundedCornerShape(12.dp)
			)
			.border(
				BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
				RoundedCornerShape(12.dp)
			)
			.padding(16.dp),
		verticalAlignment = Alignment.Top,
		horizontalArrangement = Arrangement.spacedBy(12.dp)
	) {
		Box(modifier = Modifier.padding(iconPadding)) {
			leadingIcon?.invoke()
		}

		Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
			Text(
				text = title,
				style = MaterialTheme.typography.bodyMedium.copy(
					fontSize = 16.sp,
					fontWeight = FontWeight.Bold
				),
				fontWeight = FontWeight.SemiBold,
				color = MaterialTheme.colorScheme.onPrimaryContainer
			)
			Text(
				text = description,
				style = MaterialTheme.typography.bodyMedium.copy(
					fontSize = 15.sp,
					fontWeight = FontWeight.Medium
				),
				fontWeight = FontWeight.Medium,
				color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
			)
		}
	}
}
