package dev.apercorn.koin.ui.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.AllIcons
import compose.icons.TablerIcons
import compose.icons.tablericons.*


object IconProvider {
	private val allIcons: Map<String, ImageVector> by lazy {
		TablerIcons.AllIcons.associateBy { it.name.substringAfterLast('.') }
	}

	val iconPalette: Map<String, ImageVector> = mapOf(
		// Finance
		"building-bank" to TablerIcons.BuildingBank,
		"cash" to TablerIcons.Cash,
		"cash-banknote" to TablerIcons.CashBanknote,
		"credit-card" to TablerIcons.CreditCard,
		"wallet" to TablerIcons.Wallet,
		"coin" to TablerIcons.Coin,
		"currency-dollar" to TablerIcons.CurrencyDollar,
		"receipt" to TablerIcons.Receipt,
		"report-money" to TablerIcons.ReportMoney,
		"chart-bar" to TablerIcons.ChartBar,
		"trending-up" to TablerIcons.TrendingUp,
		"arrow-up-circle" to TablerIcons.ArrowUpCircle,

		// Food & Dining
		"utensils" to TablerIcons.ToolsKitchen,
		"pizza" to TablerIcons.Pizza,
		"coffee" to TablerIcons.Mug,
		"ice-cream" to TablerIcons.IceCream,
		"glass-full" to TablerIcons.GlassFull,

		// Transport
		"car" to TablerIcons.Car,
		"bus" to TablerIcons.Bus,
		"plane" to TablerIcons.Plane,
		"bike" to TablerIcons.Bike,
		"ship" to TablerIcons.Ship,

		// Shopping
		"shopping-bag" to TablerIcons.Basket,
		"shopping-cart" to TablerIcons.ShoppingCart,
		"tag" to TablerIcons.Tag,
		"gift" to TablerIcons.Gift,

		// Home & Housing
		"home" to TablerIcons.Home,
		"home-2" to TablerIcons.Home2,
		"building" to TablerIcons.Building,
		"bed" to TablerIcons.Bed,

		// Bills & Utilities
		"file-invoice" to TablerIcons.FileInvoice,
		"bolt" to TablerIcons.Bolt,
		"droplet" to TablerIcons.Droplet,
		"wifi" to TablerIcons.Wifi,
		"phone" to TablerIcons.Phone,

		// Entertainment
		"movie" to TablerIcons.Movie,
		"music" to TablerIcons.Music,
		"device-gamepad" to TablerIcons.DeviceGamepad,
		"ball-football" to TablerIcons.BallFootball,
		"device-tv" to TablerIcons.DeviceTv,

		// Health
		"heart" to TablerIcons.Heart,
		"ambulance" to TablerIcons.Ambulance,
		"stethoscope" to TablerIcons.Stethoscope,
		"pill" to TablerIcons.Pill,

		// Education
		"book" to TablerIcons.Book,
		"school" to TablerIcons.School,
		"pencil" to TablerIcons.Pencil,
		"bookmarks" to TablerIcons.Bookmarks,

		// Income & Work
		"briefcase" to TablerIcons.Briefcase,

		// General / Misc
		"star" to TablerIcons.Star,
		"settings" to TablerIcons.Settings,
		"user" to TablerIcons.User,
		"world" to TablerIcons.World,
		"bell" to TablerIcons.Bell,
		"flag" to TablerIcons.Flag,
		"dots" to TablerIcons.Dots,
		"archive" to TablerIcons.Archive,
		"palette" to TablerIcons.Palette,
		"photo" to TablerIcons.Photo,
	)

	val defaultIcon: ImageVector = TablerIcons.QuestionMark

	fun resolve(iconName: String): ImageVector {
		return iconPalette[iconName]
			?: allIcons[iconName.kebabToPascalCase()]
			?: defaultIcon
	}

	fun parseColor(hex: String, fallback: Color = Color(0xFF26547B)): Color {
		val cleanHex = hex.removePrefix("#")
		return try {
			when (cleanHex.length) {
				6 -> Color(cleanHex.toLong(16) or 0xFF000000L)
				8 -> Color(cleanHex.toLong(16))
				else -> fallback
			}
		} catch (_: Exception) {
			fallback
		}
	}

	private fun String.kebabToPascalCase(): String {
		return split('-')
			.joinToString("") { it.replaceFirstChar { char -> char.uppercase() } }
	}
}
