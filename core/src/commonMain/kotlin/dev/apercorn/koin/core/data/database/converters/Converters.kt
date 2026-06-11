package dev.apercorn.koin.core.data.database.converters

import androidx.room.TypeConverter
import dev.apercorn.koin.core.domain.model.RecurringSchedule
import dev.apercorn.koin.core.domain.model.Transaction
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.serialization.json.Json

// Configure Json
private val json = Json {
	classDiscriminator = "type"
	ignoreUnknownKeys = true
}

class Converters {
	@TypeConverter
	fun fromInstant(value: Long?): Instant? = value?.let { Instant.fromEpochMilliseconds(it) }

	@TypeConverter
	fun toInstant(value: Instant?): Long? = value?.toEpochMilliseconds()

	@TypeConverter
	fun fromLocalDate(value: String?): LocalDate? = value?.let { LocalDate.parse(it) }

	@TypeConverter
	fun toLocalDate(value: LocalDate?): String? = value?.toString()

	// RecurringSchedule JSON conversion
	@TypeConverter
	fun fromRecurringSchedule(schedule: RecurringSchedule?): String? {
		return schedule?.let { json.encodeToString(it) }
	}

	@TypeConverter
	fun toRecurringSchedule(value: String?): RecurringSchedule? {
		return value?.let { json.decodeFromString<RecurringSchedule>(it) }
	}

	// Transaction JSON conversion
	@TypeConverter
	fun fromTransaction(transaction: Transaction?): String? {
		return transaction?.let { json.encodeToString(it) }
	}

	@TypeConverter
	fun toTransaction(value: String?): Transaction? {
		return value?.let { json.decodeFromString<Transaction>(it) }
	}
}
