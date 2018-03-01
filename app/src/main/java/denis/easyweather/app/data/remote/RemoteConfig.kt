package denis.easyweather.app.data.remote

class RemoteConfig {
    companion object {
        //        const val BASE_API_LAYER = "https://api.darksky.net/forecast/"
        val BASE_URL: String = "http://api.openweathermap.org/data/2.5/"
        //val ACCESS_KEY_API_LAYER = "22b94c02fb5392e4ed7e2b282aeef163"
        val API_KEY: String = "APPID=99d2ca8d62e1b1b71672f3e69b573710"
//        val BASE_URL = WEATHER_URL + API_KEY
        val GEOCODING_BASE_URL = "https://maps.googleapis.com/maps/api/geocode/"
    }
}