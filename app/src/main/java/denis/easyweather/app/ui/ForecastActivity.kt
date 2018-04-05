package denis.easyweather.app.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import denis.easyweather.app.R
import denis.easyweather.app.dto.ForecastDTO
import denis.easyweather.app.utils.Constants.FORECAST_RESPONSE
import kotlinx.android.synthetic.main.activity_forecast.*
import kotlinx.android.synthetic.main.item_forecast_day.view.*
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

        val days = arrayListOf(firstDay, secondDay, thirdDay, fourthDay, fifthDay, lastDay)
        for (day in days){
            val dayView = this.layoutInflater.inflate(R.layout.item_forecast_day, horizontalLayout, false) as CardView
            dayView.date.text = day.map { it.dt_txt }.first()
            dayView.times.text = day.map { it -> it.dt_txt.plus("\n") }.toString()
            horizontalLayout.addView(dayView)
        }


        val firstDayMins = firstDay.map { it.main!!.tempMin!! }.min()
        val firstDayMaxes = firstDay.map { it.main!!.tempMax!! }.max()

        val secondDayMins = secondDay.map { it.main!!.tempMin!! }.min()
        val secondDayMaxes = secondDay.map { it.main!!.tempMax!! }.max()

        val thirdDayMins = thirdDay.map { it.main!!.tempMin!! }.min()
        val thirdDayMaxes = thirdDay.map { it.main!!.tempMax!! }.max()

        val fourthDayMins = fourthDay.map { it.main!!.tempMin!! }.min()
        val fourthDayMaxes = fourthDay.map { it.main!!.tempMax!! }.max()

        val fifthDayMins = fifthDay.map { it.main!!.tempMin!! }.min()
        val fifthDayMaxes = fifthDay.map { it.main!!.tempMax!! }.max()

        val lastDayMins = lastDay.map { it.main!!.tempMin!! }.min()
        val lastDayMaxes = lastDay.map { it.main!!.tempMax!! }.max()

//        first_day.text = "min: " + firstDayMins!!.roundToInt() + "\n" + "max: " + firstDayMaxes!!.roundToInt()
//        second_day.text = "min: " + secondDayMins!!.roundToInt() + "\n" + "max: " + secondDayMaxes!!.roundToInt()
//        third_day.text = "min: " + thirdDayMins!!.roundToInt() + "\n" + "max: " + thirdDayMaxes!!.roundToInt()
//        fourth_day.text = "min: " + fourthDayMins!!.roundToInt() + "\n" + "max: " + fourthDayMaxes!!.roundToInt()
//        fifth_day.text = "min: " + fifthDayMins!!.roundToInt() + "\n" + "max: " + fifthDayMaxes!!.roundToInt()
//        last_day.text = "min: " + lastDayMins!!.roundToInt() + "\n" + "max: " + lastDayMaxes!!.roundToInt()

        val description = thirdDay.map { it.weatherEntryList!!.first().description}
//        Log.d("description", description.toString())
    }

    private fun newLiner(list: List<String>){
        return list.forEach { it.plus("/n") }

    }
}
