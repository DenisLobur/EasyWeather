package denis.easyweather.app.data

import android.content.ContentValues
import android.content.Context
import android.database.ContentObserver
import android.database.Cursor
import android.net.Uri
import android.os.Handler
import android.os.HandlerThread
import android.test.AndroidTestCase

//import denis.easyweather.app.utils.PollingCheck;

/*
    Students: These are functions and some test data to make it easier to test your database and
    Content Provider.  Note that you'll want your WeatherContract class to exactly match the one
    in our solution to use these as-given.
 */
class TestUtilities : AndroidTestCase() {

    /*
        Students: The functions we provide inside of TestProvider use this utility class to test
        the ContentObserver callbacks using the PollingCheck class that we grabbed from the Android
        CTS tests.

        Note that this only tests that the onChange function is called; it does not test that the
        correct Uri is returned.
     */
    internal class TestContentObserver private constructor(val mHT: HandlerThread) : ContentObserver(Handler(mHT.looper)) {
        var mContentChanged: Boolean = false

        // On earlier versions of Android, this onChange method is called
        override fun onChange(selfChange: Boolean) {
            onChange(selfChange, null)
        }

        override fun onChange(selfChange: Boolean, uri: Uri?) {
            mContentChanged = true
        }

        fun waitForNotificationOrFail() {
            // Note: The PollingCheck class is taken from the Android CTS (Compatibility Test Suite).
            // It's useful to look at the Android CTS source for ideas on how to test your Android
            // applications.  The reason that PollingCheck works is that, by default, the JUnit
            // testing framework is not running on the main Android application thread.
            //            new PollingCheck(5000) {
            //                @Override
            //                protected boolean check() {
            //                    return mContentChanged;
            //                }
            //            }.run();
            mHT.quit()
        }

        companion object {

            val testContentObserver: TestContentObserver
                get() {
                    val ht = HandlerThread("ContentObserverThread")
                    ht.start()
                    return TestContentObserver(ht)
                }
        }
    }

    companion object {
        internal val TEST_LOCATION = "99705"
        internal val TEST_DATE = 1419033600L  // December 20th, 2014

        internal fun validateCursor(error: String, valueCursor: Cursor, expectedValues: ContentValues) {
            Assert.assertTrue("Empty cursor returned. " + error, valueCursor.moveToFirst())
            validateCurrentRecord(error, valueCursor, expectedValues)
            valueCursor.close()
        }

        internal fun validateCurrentRecord(error: String, valueCursor: Cursor, expectedValues: ContentValues) {
            val valueSet = expectedValues.valueSet()
            for ((columnName, value) in valueSet) {
                val idx = valueCursor.getColumnIndex(columnName)
                Assert.assertFalse("Column '$columnName' not found. $error", idx == -1)
                val expectedValue = value.toString()
                Assert.assertEquals("Value '" + value.toString() +
                        "' did not match the expected value '" +
                        expectedValue + "'. " + error, expectedValue, valueCursor.getString(idx))
            }
        }

        /*
        Students: Use this to create some default weather values for your database tests.
     */
        internal fun createWeatherValues(locationRowId: Long): ContentValues {
            val weatherValues = ContentValues()
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_LOC_KEY, locationRowId)
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_DATE, TEST_DATE)
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_DEGREES, 1.1)
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_HUMIDITY, 1.2)
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_PRESSURE, 1.3)
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_MAX_TEMP, 75)
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_MIN_TEMP, 65)
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_SHORT_DESC, "Asteroids")
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_WIND_SPEED, 5.5)
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_WEATHER_ID, 321)

            return weatherValues
        }

        /*
        Students: You can uncomment this helper function once you have finished creating the
        LocationEntry part of the WeatherContract.
     */
        internal fun createNorthPoleLocationValues(): ContentValues {
            // Create a new map of values, where column names are the keys
            val testValues = ContentValues()
            testValues.put(WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING, TEST_LOCATION)
            testValues.put(WeatherContract.LocationEntry.COLUMN_CITY_NAME, "North Pole")
            testValues.put(WeatherContract.LocationEntry.COLUMN_COORD_LAT, 64.7488)
            testValues.put(WeatherContract.LocationEntry.COLUMN_COORD_LONG, -147.353)

            return testValues
        }

        /*
        Students: You can uncomment this function once you have finished creating the
        LocationEntry part of the WeatherContract as well as the WeatherDbHelper.
     */
        internal fun insertNorthPoleLocationValues(context: Context): Long {
            // insert our test records into the database
            val dbHelper = WeatherDbHelper(context)
            val db = dbHelper.writableDatabase
            val testValues = TestUtilities.createNorthPoleLocationValues()

            val locationRowId: Long
            locationRowId = db.insert(WeatherContract.LocationEntry.TABLE_NAME, null, testValues)

            // Verify we got a row back.
            Assert.assertTrue("Error: Failure to insert North Pole Location Values", locationRowId != -1)

            return locationRowId
        }

        internal val testContentObserver: TestContentObserver
            get() = TestContentObserver.testContentObserver
    }
}
