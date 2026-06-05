package dev.apercorn.koin.core.domain.model

/**
 * A curated group of Tabler Icons organized by theme.
 * Users browse these when picking an icon for a category/account.
 *
 * @property name Display name of the group (e.g., "Technology", "Shopping")
 * @property icons List of tabler-icon names in this group
 */
data class IconGroup(
	val name: String,
	val icons: List<String>
)

/**
 * Curated Tabler Icons organized by category for the icon picker.
 * Maps to Tabler Icons library — see https://tabler.io/icons
 */
object IconRegistry {

	/** All groups for browsing in the icon picker */
	val groups: List<IconGroup> = listOf(
		IconGroup(
			"Finance", listOf(
				"cash", "credit-card", "wallet", "bank", "coin",
				"currency-dollar", "currency-euro", "currency-pound",
				"currency-yen", "receipt", "percentage"
			)
		),
		IconGroup(
			"Shopping", listOf(
				"shopping-bag", "shopping-cart", "tag", "gift",
				"discount", "trolley", "basket", "store"
			)
		),
		IconGroup(
			"Food & Drink", listOf(
				"utensils", "coffee", "cup", "glass", "beer",
				"cake", "cookie", "apple", "meat", "pizza"
			)
		),
		IconGroup(
			"Transport", listOf(
				"car", "bus", "plane", "ship", "bike",
				"gas-station", "road", "map-pin"
			)
		),
		IconGroup(
			"Home", listOf(
				"home", "building", "building-community", "door",
				"sofa", "lamp", "tools", "toolbox"
			)
		),
		IconGroup(
			"Technology", listOf(
				"device-laptop", "device-mobile", "device-tablet",
				"monitor", "printer", "headphones", "camera",
				"wifi", "bluetooth", "battery", "cpu"
			)
		),
		IconGroup(
			"Health & Fitness", listOf(
				"heart", "heartbeat", "activity", "run",
				"dumbbell", "scale", "first-aid", "pill",
				"stethoscope", "tooth"
			)
		),
		IconGroup(
			"Entertainment", listOf(
				"movie", "music", "theater", "gamepad",
				"ball", "trophy", "star", "fire"
			)
		),
		IconGroup(
			"Education", listOf(
				"book", "books", "school", "graduation-cap",
				"pencil", "pen", "clipboard", "library"
			)
		),
		IconGroup(
			"Communication", listOf(
				"phone", "mail", "message", "chat",
				"user", "users", "user-plus", "friends"
			)
		),
		IconGroup(
			"Nature", listOf(
				"tree", "leaf", "flower", "sun",
				"moon", "cloud", "rain", "snowflake"
			)
		),
		IconGroup(
			"Arrows & Navigation", listOf(
				"arrow-up", "arrow-down", "arrow-left", "arrow-right",
				"arrow-up-down", "chevron-up", "chevron-down",
				"refresh", "rotate", "swap",
				"trending-up", "trending-down"
			)
		),
		IconGroup(
			"Time & Calendar", listOf(
				"calendar", "clock", "alarm", "hourglass",
				"calendar-time", "clock-stop", "history"
			)
		),
		IconGroup(
			"Miscellaneous", listOf(
				"star", "heart", "settings", "dots",
				"flag", "bell", "bookmark", "lock",
				"tag", "pin", "search", "plus",
				"minus", "check"
			)
		)
	)

	/** Flat list of all icon names for search/filter */
	val allIconNames: List<String> = groups.flatMap { it.icons }
}