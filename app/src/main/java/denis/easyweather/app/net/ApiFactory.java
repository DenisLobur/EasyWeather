package denis.easyweather.app.net;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 5/2/16.
 */
public class ApiFactory {
    public static final String BASE_URL = "http://api.openweathermap.org/";
    public static WeatherApi API;

    static {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        API = retrofit.create(WeatherApi.class);
    }
}
