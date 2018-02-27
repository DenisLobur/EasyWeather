package denis.easyweather.app.di

import dagger.Binds
import dagger.Module
import denis.easyweather.app.data.repository.WeatherRepository
import denis.easyweather.app.data.repository.WeatherRepositoryImpl


@Module
abstract class WeatherRepositoryModule {
    @Binds
    abstract fun bindsWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}