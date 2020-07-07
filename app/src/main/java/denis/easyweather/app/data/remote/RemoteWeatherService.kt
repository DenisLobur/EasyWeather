package denis.easyweather.app.data.remote

import denis.easyweather.app.data.remote.weatherModel.ForecastResponse
import denis.easyweather.app.data.remote.weatherModel.UVResponse
import denis.easyweather.app.data.remote.weatherModel.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface RemoteWeatherService {

    @GET("weather")
    fun requestWeatherForCityByCoordinates(
        @Query("lat") latitude: String, @Query("lon") longitude: String, @Query("APPID") apiKey: String, @Query("units") units: String): Call<WeatherResponse>

    @GET("weather")
    fun requestWeatherForCityByName(@Query("q") cityName: String, @Query("APPID") apiKey: String, @Query("units") units: String): Call<WeatherResponse>

    @GET("forecast")
    fun requestFiveDaysForecastForCityByName(@Query("q") cityName: String, @Query("APPID") apiKey: String, @Query("units") units: String): Call<ForecastResponse>

    @GET("uvi")
    fun requestUVDataByCoordinates(@Query("APPID") apiKey: String, @Query("lat") lat: String, @Query("lon") lon: String): Call<UVResponse>
}