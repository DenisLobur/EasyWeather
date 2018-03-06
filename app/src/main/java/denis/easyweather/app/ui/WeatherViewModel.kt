package denis.easyweather.app.ui

import android.arch.lifecycle.ViewModel
import denis.easyweather.app.data.repository.WeatherRepository
import javax.inject.Inject


class WeatherViewModel @Inject constructor(val weatherRepository: WeatherRepository) : ViewModel() {

    fun getWeather(cityName: String) = weatherRepository.getWeather(cityName)

    fun getCities() = weatherRepository.getCities()

    fun addCity(cityName: String) = weatherRepository.addCity(cityName)

}