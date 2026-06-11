package dev.apercorn.koin.ui.components.modal

import androidx.compose.ui.window.DialogProperties

actual fun DialogProperties.withEdgeToEdge(): DialogProperties = DialogProperties(
	dismissOnBackPress = dismissOnBackPress,
	dismissOnClickOutside = dismissOnClickOutside,
	securePolicy = securePolicy,
	usePlatformDefaultWidth = usePlatformDefaultWidth,
	decorFitsSystemWindows = false
)