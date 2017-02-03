package denis.easyweather.app.net;

import timber.log.Timber;

/**
 * Created by User on 5/2/16.
 */
public class RestApi {
    private static final String BASE_URL = "http://api.openweathermap.org/";
    public static WeatherService weatherService;

    static {
        Timber.plant(new Timber.DebugTree());
    }
}
