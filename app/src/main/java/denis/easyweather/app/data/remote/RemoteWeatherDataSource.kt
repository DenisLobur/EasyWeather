package denis.easyweather.app.data.remote

import denis.easyweather.app.data.remote.weatherModel.ForecastResponse
import denis.easyweather.app.data.remote.weatherModel.UVResponse
import denis.easyweather.app.data.remote.weatherModel.WeatherResponse
import retrofit2.Call

@Deprecated("remove after Weather repository finished")
class RemoteWeatherDataSource constructor(private val api: Api) {

   /* fun requestWeatherForCityByCoordinates(latitude: String, longitude: String): Call<WeatherResponse> =
            api.requestWeatherForCityByCoordinates(latitude, longitude, "99d2ca8d62e1b1b71672f3e69b573710", "imperial")

    fun requestWeatherForCityByName(cityName: String): Call<WeatherResponse> =
            api.requestWeatherForCityByName(cityName, "99d2ca8d62e1b1b71672f3e69b573710", "imperial")

    fun requestFiveDaysForecastForCityByName(cityName: String): Call<ForecastResponse> =
            api.requestFiveDaysForecastForCityByName(cityName, "99d2ca8d62e1b1b71672f3e69b573710", "imperial")

    fun requestUVDataByCoordinates(latitude: String, longitude: String): Call<UVResponse> =
            api.requestUVDataByCoordinates("99d2ca8d62e1b1b71672f3e69b573710", latitude, longitude)*/
}