package denis.easyweather.app.data.remote.weatherModel

import denis.easyweather.app.network.response.WeatherDTO

data class WeatherResponse(
        val dt: Long,
        val coord: Coord,
        val main: Main,
        val clouds: Clouds,
        val wind: Wind,
        val weather: List<Weather>,
        val sys: Sys,
        val name: String
)

data class Coord(val lon: Double, val lat: Double)

data class Main(val temp: Double, val pressure: Int, val humidity: Int, val temp_min: Double, val temp_max: Double)

data class Clouds(val all: Int)

data class Wind(val speed: Double, val deg: Int)

data class Weather( val id: Int, val main: String, val description: String, val icon: String)

data class ForecastResponse(val city: CityForecast,
                            val list: List<WeatherDTO>)

data class CityForecast(val name: String,
                        val coord: Coord,
                        val country: String,
                        val population: Double)

data class Sys(val country: String,
               val sunrise: Long,
               val sunset: Long)

data class UVResponse(val lat: String,
                      val lon: String,
                      val date: Long,
                      val value: Float)