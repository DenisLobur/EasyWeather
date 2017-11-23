package denis.easyweather.app.inject.components

import dagger.Component
import denis.easyweather.app.detail.DetailFragment
import denis.easyweather.app.inject.modules.MainActivityModule
import denis.easyweather.app.inject.scopes.ActivityScope
import denis.easyweather.app.main.MainFragment
import denis.easyweather.app.net.WeatherService

@ActivityScope
@Component(modules = arrayOf(MainActivityModule::class), dependencies = arrayOf(AppComponent::class))
interface ActivityComponent {

    val weatherService: WeatherService

    fun inject(mainFragment: MainFragment)

    fun inject(detailFragment: DetailFragment)
}
