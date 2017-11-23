package denis.easyweather.app.inject.components

import com.squareup.picasso.Picasso

import dagger.Component
import denis.easyweather.app.inject.modules.PicassoModule
import denis.easyweather.app.inject.modules.RestApiModule
import denis.easyweather.app.inject.scopes.ApplicationScope
import denis.easyweather.app.net.WeatherService

@ApplicationScope
@Component(modules = arrayOf(RestApiModule::class, PicassoModule::class))
interface AppComponent {

    val weatherService: WeatherService

    val picasso: Picasso
}
