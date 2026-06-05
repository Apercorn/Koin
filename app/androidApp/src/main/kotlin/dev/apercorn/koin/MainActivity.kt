package dev.apercorn.koin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.apercorn.koin.core.di.appContext
import dev.apercorn.koin.core.di.databaseModule
import dev.apercorn.koin.core.di.networkModule
import dev.apercorn.koin.core.di.repositoryModule
import dev.apercorn.koin.core.di.useCaseModule
import dev.apercorn.koin.di.screenModelModule
import org.koin.compose.KoinApplication

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		enableEdgeToEdge()
		super.onCreate(savedInstanceState)
		appContext = applicationContext

		setContent {
			KoinApplication(application = {
				modules(
					databaseModule,
					repositoryModule,
					useCaseModule,
					networkModule,
					screenModelModule
				)
			}) {
				App()
			}
		}
	}
}