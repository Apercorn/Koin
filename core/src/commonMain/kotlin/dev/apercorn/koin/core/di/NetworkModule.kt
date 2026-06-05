package dev.apercorn.koin.core.di

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
	single {
		HttpClient {
			install(ContentNegotiation) {
				json(Json {
					ignoreUnknownKeys = true
					isLenient = true
					prettyPrint = false
				})
			}
		}
	}
}