package denis.easyweather.app.data.remote.weatherModel


data class WeatherResponse(
        val coord: Coord,
        val main: Main,
        val clouds: Clouds,
        val wind: Wind,
        val weather: List<WeatherEntry>
)

data class Coord(val lon: Double, val lat: Double)

data class Main(val temp: Double, val pressure: Int, val humidity: Int, val temp_min: Double, val temp_max: Double)

data class Clouds(val all: Int)

data class Wind(val speed: Double, val deg: Int)

data class WeatherEntry(val main: String, val description: String)