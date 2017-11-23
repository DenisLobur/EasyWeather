package denis.easyweather.app.inject.modules

import dagger.Module
import dagger.Provides
import denis.easyweather.app.inject.scopes.ActivityScope
import denis.easyweather.app.main.MainPresenter
import denis.easyweather.app.net.WeatherService

@Module
class MainPresenterModule {
    @Provides
    @ActivityScope
    internal fun provideMainPresenter(weatherService: WeatherService): MainPresenter {
        return MainPresenter(weatherService)
    }
}
