package denis.easyweather.app.di

import android.content.Context
import dagger.Module
import dagger.Provides
import denis.easyweather.app.data.repository.WeatherRepository
import denis.easyweather.app.ui.WeatherViewModel
import javax.inject.Singleton

@Module
class AppModule(private val weatherApplication: WeatherApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context = weatherApplication

    @Provides
    @Singleton
    fun provideViewModel(weatherRepository: WeatherRepository): WeatherViewModel {
        return WeatherViewModel(weatherRepository)
    }

}
