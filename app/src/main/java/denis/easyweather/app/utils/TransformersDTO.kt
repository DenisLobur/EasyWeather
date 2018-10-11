package denis.easyweather.app.utils

import denis.easyweather.app.data.remote.weatherModel.ForecastResponse
import denis.easyweather.app.data.remote.weatherModel.UVResponse
import denis.easyweather.app.data.remote.weatherModel.WeatherResponse
import denis.easyweather.app.dto.*


object TransformersDTO {
    fun transformToWeatherDetailsDTO(cityName: String, weatherResponse: WeatherResponse?): WeatherDetailsDTO {
        val dt = weatherResponse?.dt
        val coord = CoordDTO(weatherResponse?.coord?.lon, weatherResponse?.coord?.lat)
        val main = MainDTO(weatherResponse?.main?.temp, weatherResponse?.main?.pressure, weatherResponse?.main?.humidity, weatherResponse?.main?.temp_min, weatherResponse?.main?.temp_max)
        val clouds = CloudsDTO(weatherResponse?.clouds?.all)
        val wind = WindDTO(weatherResponse?.wind?.speed, weatherResponse?.wind?.deg)
        val weatherEntryList = ArrayList<WeatherEntryDTO>()
        weatherResponse?.weather?.forEach {
            weatherEntryList.add(WeatherEntryDTO(it.main, it.id, it.description))
        }
        val dt_txt = weatherResponse?.dt_txt
        val sys = SysDTO(weatherResponse?.sys?.country, weatherResponse?.sys?.sunrise, weatherResponse?.sys?.sunset)
        val name = if (cityName.isEmpty()) weatherResponse?.name!! else cityName
        return WeatherDetailsDTO(
                dt = dt,
                cityName = name,
                coord = coord,
                main = main,
                clouds = clouds,
                wind = wind,
                weatherEntryList = weatherEntryList,
                dt_txt = dt_txt,
                sys = sys
        )
    }

    fun transformToForecastDetailDTO(cityName: String, forecastResponse: ForecastResponse?): ForecastDTO {
        val dt = forecastResponse?.list?.first()?.dt
        val coord = CoordDTO(forecastResponse?.city?.coord?.lon, forecastResponse?.city?.coord?.lat)
        val city = CityDTO(forecastResponse?.city?.name, coord, forecastResponse?.city?.country, forecastResponse?.city?.population)
        val weatherEntry = ArrayList<WeatherEntryDTO>()
        forecastResponse?.list?.forEach { weatherResponse ->
            weatherEntry.add(WeatherEntryDTO(weatherResponse.weather[0].main, weatherResponse.weather[0].id, weatherResponse.weather[0].description))
        }
        val list = ArrayList<WeatherDetailsDTO>()
        forecastResponse?.list?.forEachIndexed { period, weatherResponse ->
            list.add(WeatherDetailsDTO(
                    weatherResponse.dt,
                    cityName,
                    coord,
                    MainDTO(weatherResponse.main.temp, weatherResponse.main.pressure, weatherResponse.main.humidity, weatherResponse.main.temp_min, weatherResponse.main.temp_max),
                    CloudsDTO(weatherResponse.clouds.all),
                    WindDTO(weatherResponse.wind.speed, weatherResponse.wind.deg),
                    arrayListOf(weatherEntry[period]),
                    weatherResponse.dt_txt,
                    SysDTO(weatherResponse.sys.country, weatherResponse.sys.sunrise, weatherResponse.sys.sunset)))
        }

        return ForecastDTO(city = city,
                list = list)
    }

    fun transformUVDTO(uvResponse: UVResponse): UVDTO {
        val lat = uvResponse.lat
        val lon = uvResponse.lon
        val date = uvResponse.date
        val value = uvResponse.value

        return UVDTO(lat, lon, date, value)
    }

}