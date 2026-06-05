package dev.apercorn.koin.core.domain

import dev.apercorn.koin.core.data.database.entities.CategoryEntity


object SeedCategories {

	val defaults: List<CategoryEntity> = listOf(
		CategoryEntity(
			id = "cat_food",
			name = "Food & Dining",
			iconName = "utensils",
			colorHex = "#FF6B6B",
			type = "EXPENSE",
			sortOrder = 1
		),
		CategoryEntity(
			id = "cat_transport",
			name = "Transport",
			iconName = "car",
			colorHex = "#4ECDC4",
			type = "EXPENSE",
			sortOrder = 2
		),
		CategoryEntity(
			id = "cat_shopping",
			name = "Shopping",
			iconName = "shopping-bag",
			colorHex = "#FFE66D",
			type = "EXPENSE",
			sortOrder = 3
		),
		CategoryEntity(
			id = "cat_bills",
			name = "Bills & Utilities",
			iconName = "file-invoice",
			colorHex = "#A8E6CF",
			type = "EXPENSE",
			sortOrder = 4
		),
		CategoryEntity(
			id = "cat_entertainment",
			name = "Entertainment",
			iconName = "movie",
			colorHex = "#DDA0DD",
			type = "EXPENSE",
			sortOrder = 5
		),
		CategoryEntity(
			id = "cat_health",
			name = "Health",
			iconName = "heart",
			colorHex = "#FF9999",
			type = "EXPENSE",
			sortOrder = 6
		),
		CategoryEntity(
			id = "cat_education",
			name = "Education",
			iconName = "book",
			colorHex = "#87CEEB",
			type = "EXPENSE",
			sortOrder = 7
		),
		CategoryEntity(
			id = "cat_housing",
			name = "Housing",
			iconName = "home",
			colorHex = "#98D8C8",
			type = "EXPENSE",
			sortOrder = 8
		),
		CategoryEntity(
			id = "cat_salary",
			name = "Salary",
			iconName = "cash",
			colorHex = "#77DD77",
			type = "INCOME",
			sortOrder = 9
		),
		CategoryEntity(
			id = "cat_freelance",
			name = "Freelance",
			iconName = "briefcase",
			colorHex = "#FDCB6E",
			type = "INCOME",
			sortOrder = 10
		),
		CategoryEntity(
			id = "cat_investment",
			name = "Investment",
			iconName = "trending-up",
			colorHex = "#74B9FF",
			type = "INCOME",
			sortOrder = 11
		),
		CategoryEntity(
			id = "cat_gift_income",
			name = "Gifts Received",
			iconName = "gift",
			colorHex = "#E17055",
			type = "INCOME",
			sortOrder = 12
		),
		CategoryEntity(
			id = "cat_gift_expense",
			name = "Gifts Given",
			iconName = "gift",
			colorHex = "#E17055",
			type = "EXPENSE",
			sortOrder = 13
		),
		CategoryEntity(
			id = "cat_other",
			name = "Other",
			iconName = "dots",
			colorHex = "#B2BEC3",
			type = "EXPENSE",
			sortOrder = 99
		)
	)
}