package denis.easyweather.app.utils

import denis.easyweather.app.data.remote.weatherModel.WeatherResponse
import denis.easyweather.app.dto.*
import java.util.*


object TransformersDTO {
    fun transformToWeatherDetailsDTO(cityName: String, weatherResponse: WeatherResponse?): WeatherDetailsDTO {
        val coord = CoordDTO(weatherResponse?.coord?.lon, weatherResponse?.coord?.lat)
        val main = MainDTO(weatherResponse?.main?.temp, weatherResponse?.main?.pressure, weatherResponse?.main?.humidity, weatherResponse?.main?.temp_min, weatherResponse?.main?.temp_max)
        val clouds = CloudsDTO(weatherResponse?.clouds?.all)
        val wind = WindDTO(weatherResponse?.wind?.speed, weatherResponse?.wind?.deg)
        val temperatureFahrenheit: Double? = weatherResponse?.currently?.temperature
        val temperature = WeatherMathUtils.convertFahrenheitToCelsius(temperatureFahrenheit)
        val cloudCoverPercentage: Double? = weatherResponse?.currently?.cloudCover
        val windSpeed = weatherResponse?.currently?.windSpeed
        val humidity = weatherResponse?.currently?.humidity

        val hourlyWeatherList = ArrayList<HourlyWeatherDTO>()
        weatherResponse?.hourly?.data?.forEach {
            hourlyWeatherList.add(HourlyWeatherDTO(it.time.toLong(), it.temperature))
        }

        return WeatherDetailsDTO(
                cityName = cityName,
                coord = coord,
                main = main,
                clouds = clouds,
                wind = wind,
                cloudsPercentage = cloudCoverPercentage?.let { it * 100 },
                hourlyWeatherList = hourlyWeatherList
        )
    }
}