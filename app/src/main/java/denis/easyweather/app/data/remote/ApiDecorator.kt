package denis.easyweather.app.data.remote

import com.google.gson.Gson
import okhttp3.OkHttpClient

class ApiDecorator(
  val client: OkHttpClient,
  val gson: Gson,
  var api: Api
) : Api {
  override suspend fun requestWeatherForCityByCoordinates(
    latitude: String,
    longitude: String,
    apiKey: String,
    units: String
  ) = api.requestWeatherForCityByCoordinates(latitude, longitude, apiKey, units)

  override suspend fun requestWeatherForCityByName(
    cityName: String,
    apiKey: String,
    units: String
  ) = api.requestWeatherForCityByName(cityName, apiKey, units)

  override suspend fun requestFiveDaysForecastForCityByName(
    cityName: String,
    apiKey: String,
    units: String
  ) = api.requestFiveDaysForecastForCityByName(cityName, apiKey, units)

  override suspend fun requestUVDataByCoordinates(
    apiKey: String,
    lat: String,
    lon: String
  ) = api.requestUVDataByCoordinates(apiKey, lat, lon)
}