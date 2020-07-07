package denis.easyweather.app.ui

import denis.easyweather.app.data.repository.WeatherRepository


class WeatherViewModel constructor(val weatherRepository: WeatherRepository){

    fun getWeather(cityName: String) = weatherRepository.getWeather(cityName)

    fun getWeatherByCoord(latitude: Double, longitude: Double) = weatherRepository.getWeatherByCoord(latitude, longitude)

    fun getForecast(cityName: String) = weatherRepository.getFiveDaysForecast(cityName)

    fun getUVData(lat: String, lon: String) = weatherRepository.getUVData(lat, lon)

    fun getCities() = weatherRepository.getCities()

    fun addCity(cityName: String) = weatherRepository.addCity(cityName)

}