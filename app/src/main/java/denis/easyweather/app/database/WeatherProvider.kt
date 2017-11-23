package denis.easyweather.app.database

import android.annotation.TargetApi
import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri

class WeatherProvider : ContentProvider() {
    private var mOpenHelper: WeatherDbHelper? = null

    private fun getWeatherByLocationSetting(uri: Uri, projection: Array<String>?, sortOrder: String?): Cursor {
        val locationSetting = WeatherContract.WeatherEntry.getLocationSettingFromUri(uri)
        val startDate = WeatherContract.WeatherEntry.getStartDateFromUri(uri)

        val selectionArgs: Array<String>
        val selection: String

        if (startDate == 0L) {
            selection = sLocationSettingSelection
            selectionArgs = arrayOf(locationSetting)
        } else {
            selectionArgs = arrayOf(locationSetting, java.lang.Long.toString(startDate))
            selection = sLocationSettingWithStartDateSelection
        }

        return sWeatherByLocationSettingQueryBuilder.query(mOpenHelper!!.readableDatabase,
                projection,
                selection,
                selectionArgs, null, null,
                sortOrder
        )
    }

    private fun getWeatherByLocationSettingAndDate(
            uri: Uri, projection: Array<String>?, sortOrder: String?): Cursor {
        val locationSetting = WeatherContract.WeatherEntry.getLocationSettingFromUri(uri)
        val date = WeatherContract.WeatherEntry.getDateFromUri(uri)

        return sWeatherByLocationSettingQueryBuilder.query(mOpenHelper!!.readableDatabase,
                projection,
                sLocationSettingAndDaySelection,
                arrayOf(locationSetting, java.lang.Long.toString(date)), null, null,
                sortOrder
        )
    }

    /*
        Students: We've coded this for you.  We just create a new WeatherDbHelper for later use
        here.
     */
    override fun onCreate(): Boolean {
        mOpenHelper = WeatherDbHelper(context)
        return true
    }

    /*
        Students: Here's where you'll code the getType function that uses the UriMatcher.  You can
        test this by uncommenting testGetType in TestProvider.

     */
    override fun getType(uri: Uri): String? {

        // Use the Uri Matcher to determine what kind of URI this is.
        val match = sUriMatcher!!.match(uri)

        when (match) {
        // Student: Uncomment and fill out these two cases
        //            case WEATHER_WITH_LOCATION_AND_DATE:
        //            case WEATHER_WITH_LOCATION:
            WEATHER -> return WeatherContract.WeatherEntry.CONTENT_TYPE
            LOCATION -> return WeatherContract.LocationEntry.CONTENT_TYPE
            else -> throw UnsupportedOperationException("Unknown uri: " + uri)
        }
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?,
                       sortOrder: String?): Cursor? {
        // Here's the switch statement that, given a URI, will determine what kind of request it is,
        // and query the database accordingly.
        val retCursor: Cursor?
        when (sUriMatcher!!.match(uri)) {
        // "weather/*/*"
            WEATHER_WITH_LOCATION_AND_DATE -> {
                retCursor = getWeatherByLocationSettingAndDate(uri, projection, sortOrder)
            }
        // "weather/*"
            WEATHER_WITH_LOCATION -> {
                retCursor = getWeatherByLocationSetting(uri, projection, sortOrder)
            }
        // "weather"
            WEATHER -> {
                retCursor = null
            }
        // "location"
            LOCATION -> {
                retCursor = null
            }

            else -> throw UnsupportedOperationException("Unknown uri: " + uri)
        }
        retCursor!!.setNotificationUri(context!!.contentResolver, uri)
        return retCursor
    }

    /*
        Student: Add the ability to insert Locations to the implementation of this function.
     */
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val db = mOpenHelper!!.writableDatabase
        val match = sUriMatcher!!.match(uri)
        val returnUri: Uri

        when (match) {
            WEATHER -> {
                if (values != null) {
                    normalizeDate(values)
                }
                val _id = db.insert(WeatherContract.WeatherEntry.TABLE_NAME, null, values)
                if (_id > 0L)
                    returnUri = WeatherContract.WeatherEntry.buildWeatherUri(_id)
                else
                    throw android.database.SQLException("Failed to insert row into " + uri)
            }
            else -> throw UnsupportedOperationException("Unknown uri: " + uri)
        }
        context!!.contentResolver.notifyChange(uri, null)
        return returnUri
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        // Student: Start by getting a writable database

        // Student: Use the uriMatcher to match the WEATHER and LOCATION URI's we are going to
        // handle.  If it doesn't match these, throw an UnsupportedOperationException.

        // Student: A null value deletes all rows.  In my implementation of this, I only notified
        // the uri listeners (using the content resolver) if the rowsDeleted != 0 or the selection
        // is null.
        // Oh, and you should notify the listeners here.

        // Student: return the actual rows deleted
        return 0
    }

    private fun normalizeDate(values: ContentValues) {
        // normalize the date value
        if (values.containsKey(WeatherContract.WeatherEntry.COLUMN_DATE)) {
            val dateValue = values.getAsLong(WeatherContract.WeatherEntry.COLUMN_DATE)!!
            values.put(WeatherContract.WeatherEntry.COLUMN_DATE, WeatherContract.normalizeDate(dateValue))
        }
    }

    override fun update(
            uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        // Student: This is a lot like the delete function.  We return the number of rows impacted
        // by the update.
        return 0
    }

    override fun bulkInsert(uri: Uri, values: Array<ContentValues>): Int {
        val db = mOpenHelper!!.writableDatabase
        val match = sUriMatcher!!.match(uri)
        when (match) {
            WEATHER -> {
                db.beginTransaction()
                var returnCount = 0
                try {
                    for (value in values) {
                        normalizeDate(value)
                        val _id = db.insert(WeatherContract.WeatherEntry.TABLE_NAME, null, value)
                        if (_id != -1L) {
                            returnCount++
                        }
                    }
                    db.setTransactionSuccessful()
                } finally {
                    db.endTransaction()
                }
                context!!.contentResolver.notifyChange(uri, null)
                return returnCount
            }
            else -> return super.bulkInsert(uri, values)
        }
    }

    // You do not need to call this method. This is a method specifically to assist the testing
    // framework in running smoothly. You can read more at:
    // http://developer.android.com/reference/android/content/ContentProvider.html#shutdown()
    @TargetApi(11)
    override fun shutdown() {
        mOpenHelper!!.close()
        super.shutdown()
    }

    companion object {

        // The URI Matcher used by this content provider.
        private val sUriMatcher = buildUriMatcher()

        internal val WEATHER = 100
        internal val WEATHER_WITH_LOCATION = 101
        internal val WEATHER_WITH_LOCATION_AND_DATE = 102
        internal val LOCATION = 300

        private val sWeatherByLocationSettingQueryBuilder: SQLiteQueryBuilder

        init {
            sWeatherByLocationSettingQueryBuilder = SQLiteQueryBuilder()

            //This is an inner join which looks like
            //weather INNER JOIN location ON weather.location_id = location._id
            sWeatherByLocationSettingQueryBuilder.tables = WeatherContract.WeatherEntry.TABLE_NAME + " INNER JOIN " +
                    WeatherContract.LocationEntry.TABLE_NAME +
                    " ON " + WeatherContract.WeatherEntry.TABLE_NAME +
                    "." + WeatherContract.WeatherEntry.COLUMN_LOC_KEY +
                    " = " + WeatherContract.LocationEntry.TABLE_NAME +
                    "." + WeatherContract.LocationEntry._ID
        }

        //location.location_setting = ?
        private val sLocationSettingSelection =
                WeatherContract.LocationEntry.TABLE_NAME +
                        "." + WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING + " = ? "

        //location.location_setting = ? AND date >= ?
        private val sLocationSettingWithStartDateSelection =
                WeatherContract.LocationEntry.TABLE_NAME +
                        "." + WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING + " = ? AND " +
                        WeatherContract.WeatherEntry.COLUMN_DATE + " >= ? "

        //location.location_setting = ? AND date = ?
        private val sLocationSettingAndDaySelection =
                WeatherContract.LocationEntry.TABLE_NAME +
                        "." + WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING + " = ? AND " +
                        WeatherContract.WeatherEntry.COLUMN_DATE + " = ? "

        /*
        Students: Here is where you need to create the UriMatcher. This UriMatcher will
        match each URI to the WEATHER, WEATHER_WITH_LOCATION, WEATHER_WITH_LOCATION_AND_DATE,
        and LOCATION integer constants defined above.  You can test this by uncommenting the
        testUriMatcher test within TestUriMatcher.
     */
        internal fun buildUriMatcher(): UriMatcher? {
            // 1) The code passed into the constructor represents the code to return for the root
            // URI.  It's common to use NO_MATCH as the code for this case. Add the constructor below.


            // 2) Use the addURI function to match each of the types.  Use the constants from
            // WeatherContract to help define the types to the UriMatcher.


            // 3) Return the new matcher!
            return null
        }
    }
}
