package denis.easyweather.app.dto

data class WeatherDetailsDTO constructor(
        val dt: Long?,
        val cityName: String,
        val coord: CoordDTO?,
        val main: MainDTO?,
        val clouds: CloudsDTO?,
        val wind: WindDTO?,
        val weatherEntryList: List<WeatherEntryDTO>?,
        val dt_txt: String?,
        val sys: SysDTO?)

data class MainDTO constructor(val temp: Double?,
                                                  val pressure: Double?,
                                                  val humidity: Int?,
                                                  val tempMin: Double?,
                                                  val tempMax: Double?)

data class CloudsDTO constructor(val all: Int?)

data class WindDTO  constructor(val speed: Double?, val deg: Double?)

data class CoordDTO  constructor(val longitude: Double?,
                                                   val latitude: Double?)

data class WeatherEntryDTO  constructor(val main: String?, val id: Int?, val description: String?)

data class ForecastDTO  constructor(val city: CityDTO?,
                                                      val list: List<WeatherDetailsDTO>)

data class CityDTO  constructor(val name: String?,
                                                  val coordDTO: CoordDTO?,
                                                  val country: String?,
                                                  val population: Double?)

data class SysDTO  constructor(val country: String?,
                                                 val sunrise: Long?,
                                                 val sunset: Long?)

data class UVDTO  constructor(val lat: String?,
                                                val lon: String?,
                                                val date: Long?,
                                                val value: Float?)