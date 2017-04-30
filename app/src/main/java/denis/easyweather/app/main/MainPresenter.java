package denis.easyweather.app.main;

import android.util.Log;

import java.util.concurrent.Executors;

import javax.inject.Inject;

import denis.easyweather.app.common.ApiConfig;
import denis.easyweather.app.net.WeatherService;
import denis.easyweather.app.presenter.Presenter;
import denis.easyweather.app.router.Router;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter implements Presenter<MainView> {

    public static final String TAG = "MainPresenter";
    private MainView view;
    private Router router;
    private WeatherService weatherService;

    @Inject
    public MainPresenter(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    public void attachView(MainView view, Router router) {
        this.view = view;
        this.router = router;
    }

    @Override
    public void detachView() {
        view = null;
        router = null;
    }

    private static Scheduler scheduler = Schedulers.from(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));

    public void getWeatherByCity(String city) {
        weatherService.getWeatherByCityname(city, "metric", ApiConfig.API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(scheduler)
//                .map(it -> Log.d("result", it.response().body().toString()))
                .subscribe(stringResult -> {
                    //Log.d("result", stringResult.response().body().toString());
                    view.showWeatherRx(stringResult);
                }, throwable -> {
                    Throwable th = throwable;
                });
    }

    public void getWeatherForecast(String cityCountryName) {
        weatherService.getWeatherForecastByCityname(cityCountryName, ApiConfig.API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(scheduler)
                .map(it -> Log.d("result", it.response().body().toString()))
                .subscribe(stringResult -> {
//                    Log.d("result", stringResult.response().body().toString());
                }, throwable -> {
                    Throwable th = throwable;
                });
    }
}
