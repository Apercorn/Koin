package dev.apercorn.koin.core.domain.model

data class Category(
	/** Unique identifier for the category */
	val id: String,
	/** Name of the category */
	val name: String,
	/** Icon name for visual representation */
	val iconName: String,
	/** Hex color code for UI customization */
	val colorHex: String,
	/** Type of category (INCOME, EXPENSE) */
	val type: CategoryType,
	/** Manual order in which categories are displayed */
	val sortOrder: Int = 0
)

enum class CategoryType {
	INCOME,
	EXPENSE;

	companion object {
		fun fromString(value: String): CategoryType = when (value.uppercase()) {
			"INCOME" -> INCOME
			"EXPENSE" -> EXPENSE
			else -> EXPENSE
		}

		fun toString(type: CategoryType): String = type.name
	}
}