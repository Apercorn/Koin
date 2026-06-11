package dev.apercorn.koin.ui.components.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.apercorn.koin.ui.theme.KoinTheme
import androidx.compose.material3.ModalBottomSheet as M3ModalBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheet(
	onDismiss: () -> Unit,
	modifier: Modifier = Modifier,
	showGrabber: Boolean = true,
	containerColor: Color = MaterialTheme.colorScheme.secondaryContainer,
	content: @Composable ColumnScope.() -> Unit
) {
	val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
	val darkTheme = isSystemInDarkTheme()

	ModalSystemBarsEffect(darkTheme)

	M3ModalBottomSheet(
		onDismissRequest = onDismiss,
		sheetState = sheetState,
		modifier = modifier,
		dragHandle = {
			if (showGrabber) {
				CompositionLocalProvider(LocalRippleConfiguration provides null) {
					Box(
						modifier = Modifier
							.fillMaxWidth()
							.clickable(
								interactionSource = remember { MutableInteractionSource() },
								indication = null,
								onClick = { }
							),
						contentAlignment = Alignment.Center
					) {
						Box(
							modifier = Modifier
								.padding(top = 12.dp, bottom = 8.dp)
								.size(width = 48.dp, height = 6.dp)
								.background(
									color = KoinTheme.colors.dragHandle,
									shape = CircleShape
								)
						)
					}
				}
			}
		},
		containerColor = containerColor,
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.fillMaxHeight(0.94f)
				.navigationBarsPadding()
				.padding(bottom = 16.dp, top = 16.dp)
		) {
			content()
		}
	}
}

@Composable
expect fun ModalSystemBarsEffect(darkTheme: Boolean)