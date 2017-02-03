package denis.easyweather.app.net;

import retrofit2.adapter.rxjava.Result;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by User on 5/2/16.
 */
public interface WeatherService {

    @GET("/data/2.5/weather")
    Observable<Result<Object>> getWeather(@Query("q") String q,
                                          //@Query("mode") String mode,
                                          //@Query("units") String units,
                                          //@Query("cnt") String cnt,
                                          @Query("appid") String apiKey)
            ;
}
