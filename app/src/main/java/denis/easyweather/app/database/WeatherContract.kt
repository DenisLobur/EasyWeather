package denis.easyweather.app.database

import android.content.ContentResolver
import android.content.ContentUris
import android.net.Uri
import android.provider.BaseColumns
import android.text.format.Time

object WeatherContract {

    // The "Content authority" is a name for the entire content provider, similar to the
    // relationship between a domain name and its website.  A convenient string to use for the
    // content authority is the package name for the app, which is guaranteed to be unique on the
    // device.
    val CONTENT_AUTHORITY = "com.example.android.sunshine.app"

    // Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
    // the content provider.
    val BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY)

    // Possible paths (appended to base content URI for possible URI's)
    // For instance, content://com.example.android.sunshine.app/weather/ is a valid path for
    // looking at weather data. content://com.example.android.sunshine.app/givemeroot/ will fail,
    // as the ContentProvider hasn't been given any information on what to do with "givemeroot".
    // At least, let's hope not.  Don't be that dev, reader.  Don't be that dev.
    val PATH_WEATHER = "weather"
    val PATH_LOCATION = "location"

    // To make it easy to query for the exact date, we normalize all dates that go into
    // the database to the start of the the Julian day at UTC.
    fun normalizeDate(startDate: Long): Long {
        // normalize the start date to the beginning of the (UTC) day
        val time = Time()
        time.set(startDate)
        val julianDay = Time.getJulianDay(startDate, time.gmtoff)
        return time.setJulianDay(julianDay)
    }

    /* Inner class that defines the table contents of the location table */
    class LocationEntry : BaseColumns {
        companion object {

            val CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_LOCATION).build()

            val CONTENT_TYPE =
                    ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LOCATION
            val CONTENT_ITEM_TYPE =
                    ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LOCATION

            // Table name
            val TABLE_NAME = "location"

            val _ID = "id"

            // The location setting string is what will be sent to openweathermap
            // as the location query.
            val COLUMN_LOCATION_SETTING = "location_setting"

            // Human readable location string, provided by the weatherService.  Because for styling,
            // "Mountain View" is more recognizable than 94043.
            val COLUMN_CITY_NAME = "city_name"

            // In order to uniquely pinpoint the location on the map when we launch the
            // map intent, we store the latitude and longitude as returned by openweathermap.
            val COLUMN_COORD_LAT = "coord_lat"
            val COLUMN_COORD_LONG = "coord_long"

            fun buildLocationUri(id: Long): Uri {
                return ContentUris.withAppendedId(CONTENT_URI, id)
            }
        }
    }

    /* Inner class that defines the table contents of the weather table */
    class WeatherEntry : BaseColumns {
        companion object {

            val CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_WEATHER).build()

            val CONTENT_TYPE =
                    ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_WEATHER
            val CONTENT_ITEM_TYPE =
                    ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_WEATHER

            val TABLE_NAME = "weather"

            val _ID = "id"

            // Column with the foreign key into the location table.
            val COLUMN_LOC_KEY = "location_id"
            // Date, stored as long in milliseconds since the epoch
            val COLUMN_DATE = "date"
            // Weather id as returned by weatherService, to identify the icon to be used
            val COLUMN_WEATHER_ID = "weather_id"

            // Short description and long description of the weather, as provided by weatherService.
            // e.g "clear" vs "sky is clear".
            val COLUMN_SHORT_DESC = "short_desc"

            // Min and max temperatures for the day (stored as floats)
            val COLUMN_MIN_TEMP = "min"
            val COLUMN_MAX_TEMP = "max"

            // Humidity is stored as a float representing percentage
            val COLUMN_HUMIDITY = "humidity"

            // Humidity is stored as a float representing percentage
            val COLUMN_PRESSURE = "pressure"

            // Windspeed is stored as a float representing windspeed  mph
            val COLUMN_WIND_SPEED = "wind"

            // Degrees are meteorological degrees (e.g, 0 is north, 180 is south).  Stored as floats.
            val COLUMN_DEGREES = "degrees"

            fun buildWeatherUri(id: Long): Uri {
                return ContentUris.withAppendedId(CONTENT_URI, id)
            }

            /*
            Student: Fill in this buildWeatherLocation function
         */
            fun buildWeatherLocation(locationSetting: String): Uri? {
                return null
            }

            fun buildWeatherLocationWithStartDate(
                    locationSetting: String, startDate: Long): Uri {
                val normalizedDate = normalizeDate(startDate)
                return CONTENT_URI.buildUpon().appendPath(locationSetting)
                        .appendQueryParameter(COLUMN_DATE, java.lang.Long.toString(normalizedDate)).build()
            }

            fun buildWeatherLocationWithDate(locationSetting: String, date: Long): Uri {
                return CONTENT_URI.buildUpon().appendPath(locationSetting)
                        .appendPath(java.lang.Long.toString(normalizeDate(date))).build()
            }

            fun getLocationSettingFromUri(uri: Uri): String {
                return uri.pathSegments[1]
            }

            fun getDateFromUri(uri: Uri): Long {
                return java.lang.Long.parseLong(uri.pathSegments[2])
            }

            fun getStartDateFromUri(uri: Uri): Long {
                val dateString = uri.getQueryParameter(COLUMN_DATE)
                if (null != dateString && dateString.length > 0)
                    return java.lang.Long.parseLong(dateString)
                else
                    return 0
            }
        }
    }
}