package denis.easyweather.app.data.remote

import denis.easyweather.app.data.remote.weatherModel.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RemoteWeatherService {

    @GET("weather?{lat},{lon}")
    fun requestWeatherForCityByCoordinates(@Path("lat") latitude: String, @Path("lon") longitude: String): Single<WeatherResponse>

    @GET("weather")
    fun requestWeatherForCityByName(@Query("q") cityName: String, @Query("APPID") apiKey: String): Single<WeatherResponse>
}