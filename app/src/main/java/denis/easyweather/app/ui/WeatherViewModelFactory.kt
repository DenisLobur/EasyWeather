package denis.easyweather.app.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import denis.easyweather.app.data.repository.WeatherRepository
import javax.inject.Inject


class WeatherViewModelFactory @Inject constructor(val weatherRepository: WeatherRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(weatherRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}