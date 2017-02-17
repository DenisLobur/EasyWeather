package denis.easyweather.app.net;

import denis.easyweather.app.model.CityModel;
import denis.easyweather.app.model.ForecastModel;
import retrofit2.adapter.rxjava.Result;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface WeatherService {

    @GET("/data/2.5/weather")
    Observable<CityModel> getWeatherByCityname(@Query("q") String q,
                                               @Query("units") String units,
                                               @Query("appid") String apiKey);

    @GET("/data/2.5/weather")
    Observable<Result<CityModel>> getWeatherByCoord(@Query("lat") String latitude,
                                                    @Query("lon") String longitude,
                                                    @Query("appid") String apiKey);

    @GET("data/2.5/forecast")
    Observable<Result<ForecastModel>> getWeatherForecastByCityname(@Query("q") String cityCountryName,
                                                                   @Query("appid") String apiKey);

}
