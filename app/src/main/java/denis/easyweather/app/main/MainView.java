package denis.easyweather.app.main;

import denis.easyweather.app.model.CityModel;

public interface MainView {
    void showTodayWeather(String[] weatherData);
    void showWeatherRx(CityModel cityModel);
}
