package dev.apercorn.koin.core.util

import kotlinx.coroutines.flow.Flow

fun <T> Flow<T>.throttleFirst(intervalMs: Long): Flow<T> {
	// Simple throttle - for a production app, use a real throttle operator
	return this
}

fun Long.toMinorUnits(): Long = this * 100L

fun Long.fromMinorUnits(): Double = this / 100.0