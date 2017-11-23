package denis.easyweather.app.router

import android.os.Bundle

class Screen(val type: Type, val arguments: Bundle) {

    enum class Type {
        MAIN,
        DETAIL
    }

    companion object {

        fun fromType(type: Type): Screen {
            return Screen(type, Bundle())
        }
    }
}
