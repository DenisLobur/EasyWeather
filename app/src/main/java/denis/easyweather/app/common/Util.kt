package denis.easyweather.app.common


import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

import java.text.SimpleDateFormat

object Util {

    fun convertTemperature(temperature: Double): Int {
        return Math.round(temperature).toInt()
    }

    fun formatDate(date: Long): String {
        val sdf = SimpleDateFormat("HH:mm:ss")
        return sdf.format(date)
    }

    fun hideKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
