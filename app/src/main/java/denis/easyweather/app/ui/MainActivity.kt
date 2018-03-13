package denis.easyweather.app.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import denis.easyweather.app.R
import denis.easyweather.app.di.WeatherApplication
import denis.easyweather.app.dto.WeatherDetailsDTO
import denis.easyweather.app.utils.StringFormatter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: WeatherViewModelFactory
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var viewModel: WeatherViewModel
    private val TAG: String = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WeatherApplication.appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherViewModel::class.java)

        searchCity.setOnClickListener {
            val cityName = city.text.toString()
            setupWeatherDetailObserver(cityName)
        }
    }

    private fun setupWeatherDetailObserver(city: String): Disposable? {
        return viewModel.getWeather(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ weatherResponse: WeatherDetailsDTO? ->
                    mainTemp.text = getString(R.string.main_temp,
                            StringFormatter.convertFahrenheitToCelsius(weatherResponse?.main?.temp).toString())
                    minmaxTemp.text = getString(R.string.min_max_temp,
                            StringFormatter.convertFahrenheitToCelsius(weatherResponse?.main?.tempMin).toString(),
                            StringFormatter.convertFahrenheitToCelsius(weatherResponse?.main?.tempMax).toString())
                }, { throwable -> Log.d(TAG, throwable.message) })
    }


}
