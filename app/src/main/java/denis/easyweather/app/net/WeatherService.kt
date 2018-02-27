package denis.easyweather.app.net

import denis.easyweather.app.model.CityModel
import denis.easyweather.app.model.ForecastModel
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("/data/2.5/weather")
    fun getWeatherByCityname(@Query("q") q: String,
                             @Query("units") units: String,
                             @Query("appid") apiKey: String): Observable<CityModel>

    @GET("/data/2.5/weather")
    fun getWeatherByCoord(@Query("lat") latitude: String,
                          @Query("lon") longitude: String,
                          @Query("appid") apiKey: String): Observable<Result<CityModel>>

    @GET("data/2.5/forecast")
    fun getWeatherForecastByCityname(@Query("q") cityCountryName: String,
                                     @Query("appid") apiKey: String): Observable<Result<ForecastModel>>

}
