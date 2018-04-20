package denis.easyweather.app.ui

import android.graphics.DashPathEffect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.widget.TextView
import com.androidplot.util.PixelUtils
import com.androidplot.xy.*
import denis.easyweather.app.R
import denis.easyweather.app.common.Util
import denis.easyweather.app.dto.ForecastDTO
import denis.easyweather.app.utils.Constants.FORECAST_RESPONSE
import kotlinx.android.synthetic.main.activity_forecast.*
import kotlinx.android.synthetic.main.item_forecast_day.*
import kotlinx.android.synthetic.main.item_forecast_day.view.*
import kotlinx.android.synthetic.main.label_temperature.view.*
import me.panpf.swsv.CircularLayout
import me.panpf.swsv.SpiderWebScoreView
import org.parceler.Parcels
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition


/**
 * Created by denys on 3/20/18.
 */
class ForecastActivity : AppCompatActivity() {
    val TAG: String = "ForecastActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

//    private fun setup(scores: List<Score>) {
//        val scoreArray = FloatArray(scores.size)
//        for (w in scores.indices) {
//            scoreArray[w] = scores[w].score
//        }
//        spiderWeb_mainActivity_1.setScores(10f, scoreArray)
//
//        layout_mainActivity_circular1.removeAllViews()
//        for (score in scores) {
//            val scoreTextView = LayoutInflater.from(baseContext).inflate(android.R.layout.simple_list_item_1, layout_mainActivity_circular1, false) as TextView
//            scoreTextView.text = score.score.toString() + ""
//            layout_mainActivity_circular1.addView(scoreTextView)
//        }
//    }

    class Score(val score: Float)

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
            dayView.date.text = Util.formatDay(day.map { it.dt_txt!! }.first()) + "\n" + Util.formatMonth(day.map { it.dt_txt!! }.first()).capitalize()
            dayView.times.text = day.map { it -> it.dt_txt.plus(" ").plus(it.main!!.temp).plus(" ").plus(it.main!!.humidity).plus("\n") }.toString()
            val tempList = FloatArray(8)
            day.forEachIndexed { index, it ->  tempList.set(index, it.main!!.temp!!.toFloat()) }

            drawTemperature(tempList, dayView.spiderWebTemp, dayView.circularTemp)
            horizontalLayout.addView(dayView)
        }

        drawPlot(plot)


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


    private fun drawTemperature(tempList: FloatArray, spiderWebTemp: SpiderWebScoreView, circularTemp: CircularLayout){
        val scoreArray = FloatArray(tempList.size)
        for (w in tempList.indices) {
            scoreArray[w] = tempList[w]
        }
        spiderWebTemp.setScoreColor(this.resources.getColor(R.color.high_temp_color))
        spiderWebTemp.setScores(scoreArray.max()!!.plus(10), scoreArray)

        circularTemp.removeAllViews()
        for (score in tempList) {
            val scoreTextView = LayoutInflater.from(this).inflate(R.layout.label_temperature, circularTemp, false) as TextView
            if(score != 0.0f) {
                scoreTextView.label_tmp.text = score.toString()
            }
            circularTemp.addView(scoreTextView)
        }
    }

    private fun drawPlot(plot: XYPlot){
        val domainLabels = arrayOf<Number>(1, 2, 3, 6, 7, 8, 9, 10, 13, 14)
        val series1Numbers = arrayOf<Number>(1, 4, 2, 8, 4, 16, 8, 32, 16, 64)
        val series2Numbers = arrayOf<Number>(5, 2, 10, 5, 20, 10, 40, 20, 80, 40)

        // turn the above arrays into XYSeries':
        // (Y_VALS_ONLY means use the element index as the x value)
        val series1 = SimpleXYSeries(arrayListOf(1, 4, 2, 8, 4, 16, 8, 32, 16, 64), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series1")
        val series2 = SimpleXYSeries(arrayListOf(5, 2, 10, 5, 20, 10, 40, 20, 80, 40), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series2")

        // create formatters to use for drawing a series using LineAndPointRenderer
        // and configure them from xml:
        val series1Format = LineAndPointFormatter(this, R.xml.line_point_formatter_with_labels)

        val series2Format = LineAndPointFormatter(this, R.xml.line_point_formatter_with_labels_2)

        // add an "dash" effect to the series2 line:
        series2Format.linePaint.pathEffect = DashPathEffect(floatArrayOf(

                // always use DP when specifying pixel sizes, to keep things consistent across devices:
                PixelUtils.dpToPix(20f), PixelUtils.dpToPix(15f)), 0f)

        // just for fun, add some smoothing to the lines:
        // see: http://androidplot.com/smooth-curves-and-androidplot/
        series1Format.interpolationParams = CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal)

        series2Format.interpolationParams = CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal)

        // add a new series' to the xyplot:
        plot.addSeries(series1, series1Format)
        plot.addSeries(series2, series2Format)

        plot.graph.getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(object : Format() {
            override fun format(obj: Any, toAppendTo: StringBuffer, pos: FieldPosition): StringBuffer {
                val i = Math.round((obj as Number).toFloat())
                return toAppendTo.append(domainLabels[i])
            }

            override fun parseObject(source: String, pos: ParsePosition): Any? {
                return null
            }
        })
    }



}
