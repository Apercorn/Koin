package dev.apercorn.koin.ui.components.modal

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import compose.icons.TablerIcons
import compose.icons.tablericons.*
import dev.apercorn.koin.ui.components.CircularIcon
import androidx.compose.material3.ModalBottomSheet as M3ModalBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullscreenModal(
	title: String,
	onDismiss: () -> Unit,
	actionText: String,
	onAction: () -> Unit,
	actionEnabled: Boolean = true,
	content: @Composable ColumnScope.() -> Unit
) {
	val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
	val darkTheme = isSystemInDarkTheme()

	ModalSystemBarsEffect(darkTheme)

	M3ModalBottomSheet(
		onDismissRequest = onDismiss,
		sheetState = sheetState,
		containerColor = MaterialTheme.colorScheme.tertiaryContainer,
		dragHandle = null
	) {
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.fillMaxHeight(0.94f)
		) {
			Scaffold(
				modifier = Modifier.fillMaxSize(),
				containerColor = MaterialTheme.colorScheme.tertiaryContainer,
				topBar = {
					Box(
						modifier = Modifier
							.fillMaxWidth()
							.padding(horizontal = 15.dp, vertical = 15.dp),
						contentAlignment = Alignment.Center
					) {
						CircularIcon(
							onClick = onDismiss,
							imageVector = TablerIcons.X,
							modifier = Modifier.align(Alignment.CenterStart),
						)
						Text(
							text = title,
							style = MaterialTheme.typography.titleSmall,
							fontWeight = FontWeight.Bold
						)
					}
				},
				bottomBar = {
					Box(
						modifier = Modifier
							.fillMaxWidth()
							.padding(horizontal = 24.dp, vertical = 16.dp)
					) {
						Button(
							onClick = onAction,
							enabled = actionEnabled,
							modifier = Modifier
								.fillMaxWidth()
								.height(52.dp),
							shape = MaterialTheme.shapes.medium
						) {
							Text(
								text = actionText,
								style = MaterialTheme.typography.titleSmall,
								fontWeight = FontWeight.Bold
							)
						}
					}
				}
			) { innerPadding ->
				Column(
					modifier = Modifier
						.padding(innerPadding)
						.fillMaxSize()
						.verticalScroll(rememberScrollState())
						.padding(horizontal = 24.dp),
					horizontalAlignment = Alignment.CenterHorizontally,
					verticalArrangement = Arrangement.spacedBy(20.dp)
				) {
					content()
				}
			}
		}
	}
}
