package denis.easyweather.app.data.remote

import denis.easyweather.app.data.remote.weatherModel.ForecastResponse
import denis.easyweather.app.data.remote.weatherModel.UVResponse
import denis.easyweather.app.network.response.WeatherResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

  @GET("weather")
  suspend fun requestWeatherForCityByCoordinates(
    @Query("lat") latitude: String,
    @Query("lon") longitude: String,
    @Query("APPID") apiKey: String,
    @Query("units") units: String
  ): Response<WeatherResponseDTO>

  @GET("weather")
  suspend fun requestWeatherForCityByName(
    @Query("q") cityName: String,
    @Query("APPID") apiKey: String,
    @Query("units") units: String
  ): Response<WeatherResponseDTO>

  @GET("forecast")
  suspend fun requestFiveDaysForecastForCityByName(
    @Query("q") cityName: String,
    @Query("APPID") apiKey: String,
    @Query("units") units: String
  ): Response<ForecastResponse>

  @GET("uvi")
  suspend fun requestUVDataByCoordinates(
    @Query("APPID") apiKey: String,
    @Query("lat") lat: String,
    @Query("lon") lon: String
  ): Response<UVResponse>
}