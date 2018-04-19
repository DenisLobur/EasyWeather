package denis.easyweather.app.common


import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.text.ParseException

import java.text.SimpleDateFormat
import java.util.*

object Util {

    fun convertTemperature(temperature: Double): Int {
        return Math.round(temperature).toInt()
    }

    fun formatDateToDay(date: Long): String {
        val sdf = SimpleDateFormat("EEEE DD MMM")
        return sdf.format(date)
    }

    fun formatDay(time: String): String {
        var parsedTime = ""
        try {
            parsedTime = parseDate(time)
        } catch (e: ParseException) {
            Log.e("", e.message)
        }

        return SimpleDateFormat("EEEE", Locale.ENGLISH).format(Date(parsedTime))
    }

    fun formatMonth(time: String): String {
        var parsedTime = ""
        try {
            parsedTime = parseDate(time)
        } catch (e: ParseException) {
            Log.e("", e.message)
        }

        return SimpleDateFormat("dd MMM", Locale.ENGLISH).format(Date(parsedTime))
    }

    @Throws(ParseException::class)
    private fun parseDate(date: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        val d = sdf.parse(date)
        return d.toString()
    }

    fun hideKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun hideKeyboard(activity: Activity) {
        val view = activity.getCurrentFocus()
        if (view != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm!!.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
        }
    }
}
