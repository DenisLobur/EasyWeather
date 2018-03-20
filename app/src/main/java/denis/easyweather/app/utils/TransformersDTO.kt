package denis.easyweather.app.utils

import denis.easyweather.app.data.remote.weatherModel.ForecastResponse
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

    fun transformToForecastDetailDTO(cityName: String, forecastResponse: ForecastResponse?): ForecastDTO {
        val coord = CoordDTO(forecastResponse?.city?.coord?.lon, forecastResponse?.city?.coord?.lat)
        val city = CityDTO(forecastResponse?.city?.name, coord, forecastResponse?.city?.country, forecastResponse?.city?.population)
        val weatherEntry = forecastResponse?.list?.first()
        val main = MainDTO(
                weatherEntry?.main?.temp,
                weatherEntry?.main?.pressure,
                weatherEntry?.main?.humidity,
                weatherEntry?.main?.temp_min,
                weatherEntry?.main?.temp_max)
        val clouds = CloudsDTO(weatherEntry?.clouds?.all)
        val wind = WindDTO(weatherEntry?.wind?.speed, weatherEntry?.wind?.deg)

        val weatherList = ArrayList<WeatherEntryDTO>()
        forecastResponse?.list?.forEach {
            weatherList.add(WeatherEntryDTO(it.weather.first().main, it.weather.first().description))
        }
        val list = ArrayList<WeatherDetailsDTO>()
        forecastResponse?.list?.forEach {
            list.add(WeatherDetailsDTO(city.name!!, coord, main, clouds, wind, weatherList))
        }

        return ForecastDTO(city = city,
                list = list)
    }

}