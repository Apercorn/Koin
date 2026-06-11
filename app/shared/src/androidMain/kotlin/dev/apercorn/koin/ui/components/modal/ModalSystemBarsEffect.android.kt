package dev.apercorn.koin.ui.components.modal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
actual fun ModalSystemBarsEffect(darkTheme: Boolean) {
	val view = LocalView.current
	SideEffect {
		var parent = view.parent
		while (parent != null && parent !is android.view.Window) {
			parent = parent.parent
		}
		
		val window = (parent as? android.view.Window)
			?: (view.context as? android.app.Activity)?.window
		
		if (window != null) {
			val insetsController = WindowCompat.getInsetsController(window, view)
			insetsController.isAppearanceLightStatusBars = !darkTheme
		}
	}
}
