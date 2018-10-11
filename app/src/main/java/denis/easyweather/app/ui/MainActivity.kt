package denis.easyweather.app.ui

import android.Manifest
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import denis.easyweather.app.R
import denis.easyweather.app.common.Util
import denis.easyweather.app.di.WeatherApplication
import denis.easyweather.app.dto.ForecastDTO
import denis.easyweather.app.dto.WeatherDetailsDTO
import denis.easyweather.app.utils.Constants.FORECAST_RESPONSE
import denis.easyweather.app.utils.StringFormatter
import denis.easyweather.app.utils.ViewUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_input_field.view.*
import org.parceler.Parcels
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: WeatherViewModelFactory
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var viewModel: WeatherViewModel
    private val TAG: String = MainActivity::class.java.simpleName
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_COARSE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                        123)

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }

        WeatherApplication.appComponent.inject(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherViewModel::class.java)

        searchCity.setOnClickListener {
            val cityName = city.input_field.text.toString()
            setupWeatherDetailObserver(cityName)
            ViewUtils.hideKeyboard(this)
        }

        fiveDaysBtn.setOnClickListener {
            val cityName = city.input_field.text.toString()
            setupForecastObserver(cityName)
        }

        fusedLocationClient.lastLocation
                .addOnSuccessListener { location : Location? ->
                    // Got last known location. In some rare situations this can be null.
                    location?.apply {
                        Log.d(TAG, "lat: "+ latitude + "lon: " + longitude)
                        setupWeatherByCoordDetailObserver(latitude, longitude)
                        ViewUtils.hideKeyboard(this@MainActivity)
                    }
                }

    }

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            Log.d(TAG, "lat: " + location.latitude + "lon: " + location.longitude);
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
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
                    val descrId = weatherResponse?.weatherEntryList?.get(0)?.id
                    weatherImg.setImageResource(mapDescrToIcon(descrId))
                    sunRise.text = getString(R.string.sunrise, StringFormatter.convertTimestampToHourFormat(weatherResponse?.sys?.sunrise, TimeZone.getDefault().getDisplayName()))
                    sunSet.text = getString(R.string.sunset, StringFormatter.convertTimestampToHourFormat(weatherResponse?.sys?.sunset, TimeZone.getDefault().getDisplayName()))
                    currentCity.text = weatherResponse?.cityName + ", " + weatherResponse?.sys?.country?.toUpperCase()

                    viewModel.getUVData(weatherResponse.coord?.latitude.toString(), weatherResponse.coord?.longitude.toString())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                val uv = uv_widget
                                uv.setUv(it.value!!)
                            }, {throwable2 -> Log.d(TAG, throwable2.message)})
                }, { throwable -> Log.d(TAG, throwable.message) })
    }

    private fun setupWeatherByCoordDetailObserver(latitude: Double, longitude: Double): Disposable? {
        return viewModel.getWeatherByCoord(latitude, longitude)
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
                    val descrId = weatherResponse?.weatherEntryList?.get(0)?.id
                    weatherImg.setImageResource(mapDescrToIcon(descrId))
                    sunRise.text = getString(R.string.sunrise, StringFormatter.convertTimestampToHourFormat(weatherResponse?.sys?.sunrise, TimeZone.getDefault().getDisplayName()))
                    sunSet.text = getString(R.string.sunset, StringFormatter.convertTimestampToHourFormat(weatherResponse?.sys?.sunset, TimeZone.getDefault().getDisplayName()))
                    currentCity.text = weatherResponse?.cityName + ", " + weatherResponse?.sys?.country?.toUpperCase()

                    viewModel.getUVData(weatherResponse.coord?.latitude.toString(), weatherResponse.coord?.longitude.toString())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                val uv = uv_widget
                                uv.setUv(it.value!!)
                            }, {throwable2 -> Log.d(TAG, throwable2.message)})
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

    private fun mapDescrToIcon(descrId: Int?): Int {

        val icon = when (descrId) {
            in (200 .. 232) -> R.drawable.ic_thunderstorm
            in (300 .. 321) -> R.drawable.ic_rain
            in (500 .. 531) -> R.drawable.ic_rain
            in (600 .. 622) -> R.drawable.ic_snow
            in (701 .. 781) -> R.drawable.ic_mist
            800 -> R.drawable.ic_clear_sky
            801 -> R.drawable.ic_few_clouds
            802 -> R.drawable.ic_scattered_clouds
            803 -> R.drawable.ic_broken_clouds
            804 -> R.drawable.ic_broken_clouds
            else -> R.drawable.ic_clear_sky
        }

        return icon
    }
}
