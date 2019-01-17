package denis.easyweather.app.ui

import android.Manifest
import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.WindowManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import denis.easyweather.app.R
import denis.easyweather.app.common.Util
import denis.easyweather.app.di.WeatherApplication
import denis.easyweather.app.dto.ForecastDTO
import denis.easyweather.app.dto.WeatherDetailsDTO
import denis.easyweather.app.utils.StringFormatter
import denis.easyweather.app.utils.ViewUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_forecast_day.view.*
import kotlinx.android.synthetic.main.view_input_field.view.*
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

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

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

        WeatherApplication.appComponent.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherViewModel::class.java)

        searchCity.setOnClickListener {
            val cityName = city.input_field.text.toString()
            setupWeatherDetailObserver(cityName)
            ViewUtils.hideKeyboard(this)
        }
        
        city.input_field.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(p0!!.length == 0) {
                    city.setError(null)
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.length != 0) {
                    city.setError(null)
                }
            }
        })
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            123 -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    fusedLocationClient.lastLocation
                            .addOnSuccessListener { location : Location? ->
                                // Got last known location. In some rare situations this can be null.
                                location?.apply {
                                    Log.d(TAG, "lat: "+ latitude + "lon: " + longitude)
                                    setupWeatherByCoordDetailObserver(latitude, longitude)
                                    ViewUtils.hideKeyboard(this@MainActivity)
                                }
                            }
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
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

    private fun setupWeatherDetailObserver(cityName: String): Disposable? {
        return viewModel.getWeather(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ weatherResponse: WeatherDetailsDTO? ->
                    city.setError(null)
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
                    sunRise.text = getString(R.string.sunrise, StringFormatter.convertTimestampToHourFormat(weatherResponse?.sys?.sunrise, TimeZone.getDefault()))
                    sunSet.text = getString(R.string.sunset, StringFormatter.convertTimestampToHourFormat(weatherResponse?.sys?.sunset, TimeZone.getDefault()))
                    currentCity.text = weatherResponse?.cityName + ", " + weatherResponse?.sys?.country?.toUpperCase()

                    setupForecastObserver(cityName)
                }, {
                    throwable -> Log.d(TAG, throwable.message)
                    //TODO: show error
                    city.setError("Not found. Try another city")
                })
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
                    sunRise.text = getString(R.string.sunrise, StringFormatter.convertTimestampToHourFormat(weatherResponse?.sys?.sunrise, TimeZone.getDefault()))
                    sunSet.text = getString(R.string.sunset, StringFormatter.convertTimestampToHourFormat(weatherResponse?.sys?.sunset, TimeZone.getDefault()))
                    currentCity.text = weatherResponse?.cityName + ", " + weatherResponse?.sys?.country?.toUpperCase()

                    setupForecastObserver(weatherResponse?.cityName)
                }, { throwable -> Log.d(TAG, throwable.message) })
    }

    private fun setupForecastObserver(city: String): Disposable? {
        return viewModel.getForecast(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ forecastResponse: ForecastDTO? ->
                    fillFiveDaysForecast(forecastResponse!!)
                }, { throwable -> Log.d(TAG, throwable.message) })
    }

    private fun fillFiveDaysForecast(forecast: ForecastDTO) {
        horizontalLayout.removeAllViews()
        var counter = 0
        val firstMidnight = forecast.list.map { it.dt_txt }.indexOfFirst { it!!.endsWith("00:00:00") }
        counter = counter + firstMidnight
        val firstDay = forecast.list.subList(0, counter)
        val secondDay = forecast.list.subList(counter, counter + 8)
        val thirdDay = forecast.list.subList(counter + 8, counter + 16)
        val fourthDay = forecast.list.subList(counter + 16, counter + 24)
        val fifthDay = forecast.list.subList(counter + 24, counter + 32)
        val lastDay = forecast.list.subList(counter + 32, forecast.list.size)

        val days = arrayListOf(firstDay, secondDay, thirdDay, fourthDay, fifthDay, lastDay)

        for (day in days){
            val dayView = this.layoutInflater.inflate(R.layout.item_forecast_day, horizontalLayout, false) as CardView
            dayView.date.text = Util.formatDay(day.map { it.dt_txt!! }.first()) + "\n" + Util.formatMonth(day.map { it.dt_txt!! }.first()).capitalize()

            val tempValuesList: List<TimeToTemperature> = day.map {
                it -> TimeToTemperature(it.dt_txt!!.split(" ").last().removeSuffix(":00"), it.main!!.tempMin!!, it.main!!.tempMin!!) }
//                    .plus(" ")
//                    .plus(StringFormatter.convertFahrenheitToCelsius(it.main!!.temp))
//                    .plus(" ")
//                    .plus(it.main!!.humidity)
//                    .plus("\n") }

            dayView.timeTempList.adapter = TempAdapter(this, tempValuesList)
            dayView.timeTempList.layoutManager = LinearLayoutManager(this)


//            for(tt in tempValuesList) {
//                val tv = TextView(this)
//                tv.setText(tt)
//                val bar = TemperatureBarView(this)
//                val timeAndTempHolder = LinearLayout(this)
//                timeAndTempHolder.orientation = LinearLayout.HORIZONTAL
//                timeAndTempHolder.addView(tv, 0)
//                timeAndTempHolder.addView(bar, 1)
                //dayView.date.addView(timeAndTempHolder)
//            }

            //dayView.time.text = TextUtils.join("", tempValuesList)
            //dayView.negative_temp
            //dayView.positive_temp
            horizontalLayout.addView(dayView)
        }
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
