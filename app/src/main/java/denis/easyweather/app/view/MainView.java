package denis.easyweather.app.view;

/**
 * Created by denis on 12/23/15.
 */
public interface MainView extends MvpView {
    void showTodayWeather(String[] weatherData);
    void showWeatherRx(String s);
}
