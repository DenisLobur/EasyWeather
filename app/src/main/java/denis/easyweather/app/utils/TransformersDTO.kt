package denis.easyweather.app.utils

import denis.easyweather.app.data.remote.weatherModel.WeatherResponse
import denis.easyweather.app.dto.*


object TransformersDTO {
    fun transformToWeatherDetailsDTO(cityName: String, weatherResponse: WeatherResponse?): WeatherDetailsDTO {
        val coord = CoordDTO(weatherResponse?.coord?.lon, weatherResponse?.coord?.lat)
        val main = MainDTO(weatherResponse?.main?.temp, weatherResponse?.main?.pressure, weatherResponse?.main?.humidity, weatherResponse?.main?.temp_min, weatherResponse?.main?.temp_max)
        val clouds = CloudsDTO(weatherResponse?.clouds?.all)
        val wind = WindDTO(weatherResponse?.wind?.speed, weatherResponse?.wind?.deg)
        val weatherEntryList = ArrayList<WeatherEntryDTO>()
        weatherResponse?.weather?.forEach {
            weatherEntryList.add(WeatherEntryDTO(it.main, it.description))
        }

        return WeatherDetailsDTO(
                cityName = cityName,
                coord = coord,
                main = main,
                clouds = clouds,
                wind = wind,
                weatherEntryList = weatherEntryList
        )
    }
}