package denis.easyweather.app.data.remote

import denis.easyweather.app.data.remote.weatherModel.ForecastResponse
import denis.easyweather.app.data.remote.weatherModel.UVResponse
import denis.easyweather.app.data.remote.weatherModel.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RemoteWeatherService {

    @GET("weather?{lat},{lon}")
    fun requestWeatherForCityByCoordinates(@Path("lat") latitude: String, @Path("lon") longitude: String): Single<WeatherResponse>

    @GET("weather")
    fun requestWeatherForCityByName(@Query("q") cityName: String, @Query("APPID") apiKey: String, @Query("units") units: String): Single<WeatherResponse>

    @GET("forecast")
    fun requestFiveDaysForecastForCityByName(@Query("q") cityName: String, @Query("APPID") apiKey: String, @Query("units") units: String): Single<ForecastResponse>

    @GET("uvi")
    fun requestUVDataByCoordinates(@Query("APPID") apiKey: String, @Query("lat") lat: String, @Query("lon") lon: String): Single<UVResponse>
}