package denis.easyweather.app.main;

import denis.easyweather.app.model.CityModel;

/**
 * Created by denis on 12/23/15.
 */
public interface MainView {
    void showTodayWeather(String[] weatherData);
    void showWeatherRx(CityModel cityModel);
}
