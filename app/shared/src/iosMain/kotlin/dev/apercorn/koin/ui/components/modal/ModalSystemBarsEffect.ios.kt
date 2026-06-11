package dev.apercorn.koin.ui.components.modal

import androidx.compose.runtime.Composable

@Composable
actual fun ModalSystemBarsEffect(darkTheme: Boolean) {
	// No-op for iOS as it handles this differently via info.plist or controller overrides
}
