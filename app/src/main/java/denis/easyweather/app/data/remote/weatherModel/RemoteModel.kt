package denis.easyweather.app.data.remote.weatherModel


data class WeatherResponse(
        val dt: Long,
        val coord: Coord,
        val main: Main,
        val clouds: Clouds,
        val wind: Wind,
        val weather: List<WeatherEntry>,
        val dt_txt: String
)

data class Coord(val lon: Double, val lat: Double)

data class Main(val temp: Double, val pressure: Double, val humidity: Int, val temp_min: Double, val temp_max: Double)

data class Clouds(val all: Int)

data class Wind(val speed: Double, val deg: Double)

data class WeatherEntry(val main: String, val description: String)

data class ForecastResponse(val city: CityForecast,
                            val list: List<WeatherResponse>)

data class CityForecast(val name: String,
                        val coord: Coord,
                        val country: String,
                        val population: Double)