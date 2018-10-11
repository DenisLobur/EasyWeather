package denis.easyweather.app.ui

import android.arch.lifecycle.ViewModel
import denis.easyweather.app.data.repository.WeatherRepository
import javax.inject.Inject


class WeatherViewModel @Inject constructor(val weatherRepository: WeatherRepository) : ViewModel() {

    fun getWeather(cityName: String) = weatherRepository.getWeather(cityName)

    fun getWeatherByCoord(latitude: Double, longitude: Double) = weatherRepository.getWeatherByCoord(latitude, longitude)

    fun getForecast(cityName: String) = weatherRepository.getFiveDaysForecast(cityName)

    fun getUVData(lat: String, lon: String) = weatherRepository.getUVData(lat, lon)

    fun getCities() = weatherRepository.getCities()

    fun addCity(cityName: String) = weatherRepository.addCity(cityName)

}