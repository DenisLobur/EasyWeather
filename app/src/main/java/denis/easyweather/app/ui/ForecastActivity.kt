package denis.easyweather.app.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import denis.easyweather.app.R
import denis.easyweather.app.dto.ForecastDTO
import denis.easyweather.app.utils.Constants.FORECAST_RESPONSE
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
        val dates = forecast.list.map { list ->
            list.dt_txt
        }.chunked(8)
        val first = dates.first()
        var firsTxt = ""
        first.forEach {
            firsTxt = firsTxt + it + "\n"
        }

        first_day.text = firsTxt

        val second = dates.get(1)
        var secondTxt = ""
        second.forEach {
            secondTxt = secondTxt + it + "\n"
        }

        second_day.text = secondTxt

        val third = dates.get(2)
        var thirdTxt = ""
        third.forEach {
            thirdTxt = thirdTxt + it + "\n"
        }

        third_day.text = thirdTxt

        val fourth = dates.get(3)
        var fourthTxt = ""
        fourth.forEach {
            fourthTxt = fourthTxt + it + "\n"
        }

        fourth_day.text = fourthTxt

        val fifth = dates.get(4)
        var fifthTxt = ""
        fifth.forEach {
            fifthTxt = fifthTxt + it + "\n"
        }

        fifth_day.text = fifthTxt
    }
}
