package denis.easyweather.app.model

class ForecastModel {
    var city: City? = null
    var list: List<ForecastData>? = null

    inner class City {
        var id: Long? = null
        var name: String? = null
        var coord: Coordinates? = null
        var country: String? = null
        var cod: Int? = null

        inner class Coordinates {
            var lat: Double? = null
            var lon: Double? = null
        }
    }

    inner class ForecastData {
        var dt: Long? = null
        var main: Main? = null
        var weathers: List<Weather>? = null
        var clouds: Clouds? = null
        var wind: Wind? = null
        var dt_txt: String? = null

        inner class Main {
            var temp: Double? = null
            var temp_min: Double? = null
            var temp_max: Double? = null
            var pressure: Double? = null
            var sea_level: Double? = null
            var grnd_level: Double? = null
            var humidity: Int? = null
            var temp_kf: Double? = null
        }

        inner class Weather {
            var id: Long? = null
            var main: String? = null
            var description: String? = null
            var icon: String? = null
        }

        inner class Clouds {
            var all: Int? = null
        }

        inner class Wind {
            var speed: Double? = null
            var deg: Double? = null
        }
    }

    override fun toString(): String {
        return "ForecastModel{" +
                "city=" + city!!.country +
                ", list=" + list!![0].wind!!.deg +
                ", dt_txt='" + list!![0].dt_txt + '\'' +
                '}'
    }
}
