package denis.easyweather.app.data.remote

import denis.easyweather.app.data.remote.weatherModel.ForecastResponse
import denis.easyweather.app.data.remote.weatherModel.UVResponse
import denis.easyweather.app.data.remote.weatherModel.WeatherResponse
import retrofit2.Call

class RemoteWeatherDataSource constructor(private val remoteWeatherService: RemoteWeatherService) {

    fun requestWeatherForCityByCoordinates(latitude: String, longitude: String): Call<WeatherResponse> =
            remoteWeatherService.requestWeatherForCityByCoordinates(latitude, longitude, "99d2ca8d62e1b1b71672f3e69b573710", "imperial")

    fun requestWeatherForCityByName(cityName: String): Call<WeatherResponse> =
            remoteWeatherService.requestWeatherForCityByName(cityName, "99d2ca8d62e1b1b71672f3e69b573710", "imperial")

    fun requestFiveDaysForecastForCityByName(cityName: String): Call<ForecastResponse> =
            remoteWeatherService.requestFiveDaysForecastForCityByName(cityName, "99d2ca8d62e1b1b71672f3e69b573710", "imperial")

    fun requestUVDataByCoordinates(latitude: String, longitude: String): Call<UVResponse> =
            remoteWeatherService.requestUVDataByCoordinates("99d2ca8d62e1b1b71672f3e69b573710", latitude, longitude)
}