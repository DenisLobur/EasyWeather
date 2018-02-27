package denis.easyweather.app.di

import dagger.Component
import denis.easyweather.app.ui.MainActivity
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, RemoteModule::class, RoomModule::class, WeatherRepositoryModule::class))
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}
