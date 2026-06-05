package dev.apercorn.koin.ui.components.icons

/**
 * Host for the Icon Picker bottom sheet / dialog.
 *
 * TODO: Implement icon picker UI using Tabler Icons + IconRegistry.
 * - Search bar for filtering icons by name
 * - Grouped sections by IconGroup
 * - Grid of icons to pick from
 * - Selected state indicator
 * - Callback: (iconName: String) -> Unit
 *
 * Usage:
 *   IconPickerHost(
 *       selectedIcon = currentIcon,
 *       onIconSelected = { iconName -> updateIcon(iconName) },
 *       onDismiss = { dismiss() }
 *   )
 */