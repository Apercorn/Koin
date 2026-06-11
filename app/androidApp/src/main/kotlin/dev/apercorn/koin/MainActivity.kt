package dev.apercorn.koin

import android.os.Bundle
import androidx.activity.*
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import dev.apercorn.koin.core.di.*
import dev.apercorn.koin.di.screenModelModule
import org.koin.compose.KoinApplication

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		enableEdgeToEdge(
			statusBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT),
			navigationBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT)
		)
		super.onCreate(savedInstanceState)
		WindowCompat.setDecorFitsSystemWindows(window, false)
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