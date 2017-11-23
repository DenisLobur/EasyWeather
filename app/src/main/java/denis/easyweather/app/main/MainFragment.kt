package denis.easyweather.app.main

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import javax.inject.Inject

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.OnEditorAction
import butterknife.Unbinder
import denis.easyweather.app.R
import denis.easyweather.app.common.BaseFragment
import denis.easyweather.app.common.Util
import denis.easyweather.app.model.CityModel

class MainFragment : BaseFragment(), MainView {

    @BindView(R.id.city_name)
    var cityName: EditText? = null
    @BindView(R.id.search_city)
    var searchCity: Button? = null
    @BindView(R.id.latitude)
     var latitude: TextView? = null
    @BindView(R.id.longitude)
     var longitude: TextView? = null
    @BindView(R.id.temp)
     var temperature: TextView? = null
    @BindView(R.id.humidity)
     var humidity: TextView? = null
    @BindView(R.id.pressure)
     var pressure: TextView? = null
    @BindView(R.id.max_temp)
     var maxTemp: TextView? = null
    @BindView(R.id.min_temp)
     var minTemp: TextView? = null
    @BindView(R.id.main)
     var main: TextView? = null
    @BindView(R.id.wind_data)
     var wind: TextView? = null
    @BindView(R.id.clouds)
     var clouds: TextView? = null
    @BindView(R.id.sunrise)
     var sunrise: TextView? = null
    @BindView(R.id.sunset)
     var sunset: TextView? = null
    var unbinder: Unbinder? = null

    @Inject
    lateinit var presenter: MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_main, container, false)
        unbinder = ButterKnife.bind(this, rootView)

        return rootView
    }

    override fun onStart() {
        super.onStart()
        presenter!!.attachView(this, router)
    }

    override fun onStop() {
        presenter!!.detachView()
        super.onStop()
    }

    @OnEditorAction(R.id.city_name)
    fun inSearch(event: KeyEvent): Boolean {
//        if (event.keyCode == event.KEYCODE_ENTER) {
//            presenter!!.getWeatherByCity(cityName!!.text.toString())
//            return true
//        }
        //TODO: make it work!
        return false
    }

    @OnClick(R.id.search_city)
    fun onSearchClick() {
        Log.d("result", "click")
        //Util.hideKeyboard(context, view)
        //presenter.getWeatherByCity("London");
        presenter!!.getWeatherByCity(cityName!!.text.toString())
    }

    override fun onDestroyView() {
        unbinder!!.unbind()
        super.onDestroyView()
    }

    override fun showTodayWeather(weatherData: Array<String>) {

    }

    override fun showWeatherRx(cityModel: CityModel) {
        latitude!!.text = activity.resources.getString(
                R.string.latitude, cityModel.coord!!.lat.toString())
        longitude!!.text = activity.resources.getString(
                R.string.longitude, cityModel.coord!!.lon.toString())
        temperature!!.text = activity.resources.getString(
                R.string.temperature, Util.convertTemperature(cityModel.main!!.temp!!).toString())
        humidity!!.text = activity.resources.getString(
                R.string.humidity, cityModel.main!!.humidity.toString())
        pressure!!.text = activity.resources.getString(
                R.string.pressure, cityModel.main!!.pressure.toString())
        maxTemp!!.text = activity.resources.getString(
                R.string.max_temp, Util.convertTemperature(cityModel.main!!.temp_max!!).toString())
        minTemp!!.text = activity.resources.getString(
                R.string.min_temp, Util.convertTemperature(cityModel.main!!.temp_min!!).toString())
        wind!!.text = activity.resources.getString(
                R.string.wind_data, cityModel.wind!!.speed.toString(), cityModel.wind!!.deg.toString())
        clouds!!.text = activity.resources.getString(
                R.string.clouds, cityModel.clouds!!.all.toString())
        sunrise!!.text = activity.resources.getString(
                R.string.sunrise, Util.formatDate(cityModel.sys!!.sunrise!!))
        sunset!!.text = activity.resources.getString(
                R.string.sunset, Util.formatDate(cityModel.sys!!.sunset!!))
    }

    override fun inject() {
        activityComponent?.inject(this)
    }
}
