package denis.easyweather.app.main

import android.util.Log

import java.util.concurrent.Executors

import javax.inject.Inject

import denis.easyweather.app.common.ApiConfig
import denis.easyweather.app.net.WeatherService
import denis.easyweather.app.presenter.Presenter
import denis.easyweather.app.router.Router
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainPresenter @Inject
constructor(val weatherService: WeatherService) : Presenter<MainView> {
    var view: MainView? = null
    var router: Router? = null

    override fun attachView(view: MainView, router: Router?) {
        this.view = view
        this.router = router
    }

    override fun detachView() {
        view = null
        router = null
    }

    fun getWeatherByCity(city: String) {
        weatherService.getWeatherByCityname(city, "metric", ApiConfig.API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(scheduler)
                //                .map(it -> Log.d("result", it.response().body().toString()))
                .subscribe({ stringResult ->
                    //Log.d("result", stringResult.response().body().toString());
                    view!!.showWeatherRx(stringResult)
                }) { throwable -> val th = throwable }
    }

    fun getWeatherForecast(cityCountryName: String) {
        weatherService.getWeatherForecastByCityname(cityCountryName, ApiConfig.API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(scheduler)
                .map { it -> Log.d("result", it.response()?.body().toString()) }
                .subscribe({ stringResult ->
                    //                    Log.d("result", stringResult.response().body().toString());
                }) { throwable -> val th = throwable }
    }

    companion object {

        val TAG = "MainPresenter"

        val scheduler = Schedulers.from(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()))
    }
}
