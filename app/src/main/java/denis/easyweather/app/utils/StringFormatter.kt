package denis.easyweather.app.utils

import java.text.SimpleDateFormat
import java.util.*


object StringFormatter {
    val unitPercentage = "%"
    val unitDegrees = "\u00b0"
    val unitsMetresPerSecond = "m/s"
    val unitDegreesCelsius = "\u2103"

    fun convertTimestampToDayOfTheWeek(timestamp: Int): String {
        val formatter = SimpleDateFormat("EEEE", Locale.ENGLISH)
        val dayName = formatter.format(Date(timestamp.toLong() * 1000))
        return dayName
    }

    fun convertTimestampToDayAndHourFormat(timestamp: Long?): String {
        val DAY_HOUR_MINUTE = "EEEE, HH:mm"
        val formatter = SimpleDateFormat(DAY_HOUR_MINUTE, Locale.ENGLISH)

        val dateFormat = formatter.format(Date(timestamp ?: 0))
        return dateFormat
    }

    fun convertTimestampToHourFormat(timestamp: Long?, timeZone: TimeZone?): String {
        val HOUR_MINUTE = "HH:mm"
        val formatter = SimpleDateFormat(HOUR_MINUTE)
        formatter.setTimeZone(timeZone)

        val dayName = formatter.format(Date((timestamp ?: 0) * 1000))
        return dayName
    }

    fun convertToValueWithUnit(precision: Int, unitSymbol: String, value: Double?): String {
        return getPrecision(precision).format(value) + unitSymbol
    }

    private fun getPrecision(precision: Int): String {
        return "%." + precision + "f"
    }

    fun convertFahrenheitToCelsius(temp: Double?): Int {
        val celsiusTemp = (temp!! - 32) * 0.5556
        return celsiusTemp.toInt()
    }

    fun convertAngleToDirection(deg: Double): String  = when(deg){
        in 0..90 -> "SW"
        in 91..180 -> "NW"
        in 181..270 -> "NE"
        else -> "SE"
    }
}

