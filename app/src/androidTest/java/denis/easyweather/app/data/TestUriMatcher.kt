package denis.easyweather.app.data

import android.test.AndroidTestCase

/*
    Uncomment this class when you are ready to test your UriMatcher.  Note that this class utilizes
    constants that are declared with package protection inside of the UriMatcher, which is why
    the test must be in the same data package as the Android app code.  Doing the test this way is
    a nice compromise between data hiding and testability.
 */
class TestUriMatcher : AndroidTestCase() {
    companion object {
        private val LOCATION_QUERY = "London, UK"
        private val TEST_DATE = 1419033600L  // December 20th, 2014
        private val TEST_LOCATION_ID = 10L

        // content://com.example.android.sunshine.app/weather"
        private val TEST_WEATHER_DIR = WeatherContract.WeatherEntry.CONTENT_URI
        private val TEST_WEATHER_WITH_LOCATION_DIR = WeatherContract.WeatherEntry.buildWeatherLocation(LOCATION_QUERY)
        private val TEST_WEATHER_WITH_LOCATION_AND_DATE_DIR = WeatherContract.WeatherEntry.buildWeatherLocationWithDate(LOCATION_QUERY, TEST_DATE)
        // content://com.example.android.sunshine.app/location"
        private val TEST_LOCATION_DIR = WeatherContract.LocationEntry.CONTENT_URI
    }

    /*
        Students: This function tests that your UriMatcher returns the correct integer value
        for each of the Uri types that our ContentProvider can handle.  Uncomment this when you are
        ready to test your UriMatcher.
     */
    //    public void testUriMatcher() {
    //        UriMatcher testMatcher = WeatherProvider.buildUriMatcher();
    //
    //        assertEquals("Error: The WEATHER URI was matched incorrectly.",
    //                testMatcher.match(TEST_WEATHER_DIR), WeatherProvider.WEATHER);
    //        assertEquals("Error: The WEATHER WITH LOCATION URI was matched incorrectly.",
    //                testMatcher.match(TEST_WEATHER_WITH_LOCATION_DIR), WeatherProvider.WEATHER_WITH_LOCATION);
    //        assertEquals("Error: The WEATHER WITH LOCATION AND DATE URI was matched incorrectly.",
    //                testMatcher.match(TEST_WEATHER_WITH_LOCATION_AND_DATE_DIR), WeatherProvider.WEATHER_WITH_LOCATION_AND_DATE);
    //        assertEquals("Error: The LOCATION URI was matched incorrectly.",
    //                testMatcher.match(TEST_LOCATION_DIR), WeatherProvider.LOCATION);
    //    }
}
