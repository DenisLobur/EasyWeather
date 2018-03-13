package denis.easyweather.app.utils

import denis.easyweather.app.data.remote.weatherModel.WeatherResponse
import denis.easyweather.app.dto.CoordDTO
import denis.easyweather.app.dto.HourlyWeatherDTO
import denis.easyweather.app.dto.MainDTO
import denis.easyweather.app.dto.WeatherDetailsDTO
import java.util.*


object TransformersDTO {
    fun transformToWeatherDetailsDTO(cityName: String, weatherResponse: WeatherResponse?): WeatherDetailsDTO {
        val coord = CoordDTO(weatherResponse?.coord?.lon, weatherResponse?.coord?.lat)
        val main = MainDTO(weatherResponse?.main?.temp, weatherResponse?.main?.pressure, weatherResponse?.main?.humidity, weatherResponse?.main?.temp_min, weatherResponse?.main?.temp_max)
        val temperatureFahrenheit: Double? = weatherResponse?.currently?.temperature
        val temperature = WeatherMathUtils.convertFahrenheitToCelsius(temperatureFahrenheit)
        val cloudCoverPercentage: Double? = weatherResponse?.currently?.cloudCover
        val windSpeed = weatherResponse?.currently?.windSpeed
        val humidity = weatherResponse?.currently?.humidity

        val hourlyWeatherList = ArrayList<HourlyWeatherDTO>()
        weatherResponse?.hourly?.data?.forEach {
            hourlyWeatherList.add(HourlyWeatherDTO(it.time.toLong(), it.temperature))
        }

        var hourlyWeatherStringFormatedHoursList = ArrayList<String>()

        //temperature for only next 24hours
        if (hourlyWeatherList.size > 24) {
            hourlyWeatherStringFormatedHoursList = (0..24).mapTo(ArrayList<String>()) {
                StringFormatter.convertTimestampToHourFormat(timestamp = hourlyWeatherList[it].timestamp, timeZone = weatherResponse?.timezone)
            }
        }

        return WeatherDetailsDTO(
                cityName = cityName,
                coord = coord,
                main = main,
                windSpeed = windSpeed,
                humidity = humidity?.let { it * 100 },
                cloudsPercentage = cloudCoverPercentage?.let { it * 100 },
                hourlyWeatherList = hourlyWeatherList,
                hourlyWeatherStringFormatedHoursList = hourlyWeatherStringFormatedHoursList
        )
    }
}