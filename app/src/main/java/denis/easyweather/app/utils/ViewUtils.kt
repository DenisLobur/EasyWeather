package denis.easyweather.app.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager


object ViewUtils {

    fun hideKeyboard(activity: Activity){
        val view = activity.getCurrentFocus()
        if (view != null) {
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
        }
    }
}