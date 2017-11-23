package denis.easyweather.app.model

class CityModel {
    var coord: Coordinates? = null
    var sys: SunData? = null
    var weather: List<WeatherDescription>? = null
    var main: WeatherData? = null
    var wind: WindData? = null
    var rain: RainData? = null
    var clouds: Clouds? = null
    var dt: Long? = null
    var id: Long? = null
    var name: String? = null
    var cod: Int? = null

    inner class WeatherData {
        var temp: Double? = null
        var humidity: Double? = null
        var pressure: Double? = null
        var temp_min: Double? = null
        var temp_max: Double? = null
    }

    inner class Coordinates {
        var lat: Double? = null
        var lon: Double? = null
    }

    inner class SunData {
        var country: String? = null
        var sunrise: Long? = null
        var sunset: Long? = null
    }

    inner class WeatherDescription {
        var id: Long? = null
        var main: String? = null
        var description: String? = null
        var icon: String? = null
    }

    inner class WindData {
        var speed: Double? = null
        var deg: Double? = null
    }

    inner class RainData {
        var _3h: Int? = null
    }

    inner class Clouds {
        var all: Int? = null
    }
}
