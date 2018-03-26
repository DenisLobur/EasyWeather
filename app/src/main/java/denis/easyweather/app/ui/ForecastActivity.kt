package denis.easyweather.app.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import denis.easyweather.app.R
import denis.easyweather.app.dto.ForecastDTO
import denis.easyweather.app.utils.Constants.FORECAST_RESPONSE
import denis.easyweather.app.utils.StringFormatter
import kotlinx.android.synthetic.main.activity_forecast.*
import org.parceler.Parcels

/**
 * Created by denys on 3/20/18.
 */
class ForecastActivity : AppCompatActivity() {
    val TAG: String = "ForecastActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO: fill with widgets
        setContentView(R.layout.activity_forecast)
        val weatherDetails = Parcels.unwrap<ForecastDTO>(intent.getParcelableExtra(FORECAST_RESPONSE))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = weatherDetails.city?.name

        fillFiveDaysForecast(weatherDetails)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun fillFiveDaysForecast(forecast: ForecastDTO) {
        var counter = 0
        val firstMidnight = forecast.list.map { it.dt_txt }.indexOfFirst { it!!.endsWith("00:00:00") }
        counter = counter + firstMidnight
        val firstDay = forecast.list.subList(0, counter)
        val secondDay = forecast.list.subList(counter, counter + 8)
        val thirdDay = forecast.list.subList(counter + 8, counter + 16)
        val fourthDay = forecast.list.subList(counter + 16, counter + 24)
        val fifthDay = forecast.list.subList(counter + 24, counter + 32)
        val lastDay = forecast.list.subList(counter + 32, forecast.list.size)
        val firstDayMins = firstDay.map { it.main!!.tempMin!! }.average()
        val firstDayMaxes = firstDay.map { it.main!!.tempMax!! }.average()
        Log.d(TAG, "mins: " + firstDayMins + " maxes: " + firstDayMaxes)
        first_day.text = "min: " + StringFormatter.convertFahrenheitToCelsius(firstDayMins) + "\n" + "max: " + StringFormatter.convertFahrenheitToCelsius(firstDayMaxes)
//        Log.d(TAG, firstDay.toString())
//        Log.d(TAG, secondDay.toString())
//        Log.d(TAG, thirdDay.toString())
//        Log.d(TAG, fourthDay.toString())
//        Log.d(TAG, fifthDay.toString())
//        Log.d(TAG, lastDay.toString())
    }
}
