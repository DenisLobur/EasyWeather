package denis.easyweather.app.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import denis.easyweather.app.R
import denis.easyweather.app.dto.ForecastDTO
import denis.easyweather.app.utils.Constants.FORECAST_RESPONSE
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
        forecast.list.forEach { list ->
            Log.d(TAG, list.dt_txt)
        }
    }
}
