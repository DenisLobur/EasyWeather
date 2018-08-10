package denis.easyweather.app.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import denis.easyweather.app.R
import denis.easyweather.app.common.Util
import denis.easyweather.app.di.WeatherApplication
import denis.easyweather.app.dto.ForecastDTO
import denis.easyweather.app.dto.WeatherDetailsDTO
import denis.easyweather.app.utils.Constants.FORECAST_RESPONSE
import denis.easyweather.app.utils.StringFormatter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_input_field.view.*
import org.parceler.Parcels
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
            val cityName = city.input_field.text.toString()
            setupWeatherDetailObserver(cityName)
        }

        fiveDaysBtn.setOnClickListener {
            val cityName = city.input_field.text.toString()
            setupForecastObserver(cityName)
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
                    pressureValue.text = getString(R.string.pressure_value, weatherResponse?.main?.pressure!!.toString())
                    humidityValue.text = getString(R.string.humidity_value, weatherResponse?.main?.humidity!!.toString())
                    cloudsValue.text = getString(R.string.clouds_value, weatherResponse?.clouds?.all!!.toString())
                    windValue.text = getString(R.string.wind_value, weatherResponse.wind?.speed!!.toString(), StringFormatter.convertAngleToDirection(weatherResponse?.wind.deg!!))
                    description.text = weatherResponse?.weatherEntryList?.get(0)?.description
                }, { throwable -> Log.d(TAG, throwable.message) })
    }

    private fun setupForecastObserver(city: String): Disposable? {
        return viewModel.getForecast(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ forecastResponse: ForecastDTO? ->
                    Util.hideKeyboard(this)
                    navigateToDetailsActivity(forecastResponse)
                }, { throwable -> Log.d(TAG, throwable.message) })
    }

    private fun navigateToDetailsActivity(forecastResponse: ForecastDTO?) {
        val intent = Intent(this, ForecastActivity::class.java)
        intent.putExtra(FORECAST_RESPONSE, Parcels.wrap(forecastResponse))
        startActivity(intent)
    }


}
