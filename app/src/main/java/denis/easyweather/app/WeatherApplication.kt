package denis.easyweather.app

import android.app.Activity
import android.app.Application

import denis.easyweather.app.inject.components.AppComponent
import denis.easyweather.app.inject.components.DaggerAppComponent
import denis.easyweather.app.inject.modules.ContextModule
import timber.log.Timber

class WeatherApplication : Application() {

    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        // Only dependency with constructor is required
        appComponent = DaggerAppComponent.builder()
                .contextModule(ContextModule(this)) // Only dependency with constructor is required
                .build()
    }

    fun appComponent(): AppComponent? {
        return appComponent
    }

    companion object {

        operator fun get(activity: Activity): WeatherApplication {
            return activity.application as WeatherApplication
        }
    }
}
