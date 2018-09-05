package denis.easyweather.app.dto

import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
data class WeatherDetailsDTO @ParcelConstructor constructor(
        val dt: Long?,
        val cityName: String,
        val coord: CoordDTO?,
        val main: MainDTO?,
        val clouds: CloudsDTO?,
        val wind: WindDTO?,
        val weatherEntryList: List<WeatherEntryDTO>?,
        val dt_txt: String?,
        val sys: SysDTO?)

@Parcel(Parcel.Serialization.BEAN)
data class MainDTO @ParcelConstructor constructor(val temp: Double?,
                                                  val pressure: Double?,
                                                  val humidity: Int?,
                                                  val tempMin: Double?,
                                                  val tempMax: Double?)

@Parcel(Parcel.Serialization.BEAN)
data class CloudsDTO @ParcelConstructor constructor(val all: Int?)

@Parcel(Parcel.Serialization.BEAN)
data class WindDTO @ParcelConstructor constructor(val speed: Double?, val deg: Double?)

@Parcel(Parcel.Serialization.BEAN)
data class CoordDTO @ParcelConstructor constructor(val longitude: Double?,
                                                   val latitude: Double?)

@Parcel(Parcel.Serialization.BEAN)
data class WeatherEntryDTO @ParcelConstructor constructor(val main: String?, val id: Int?, val description: String?)

@Parcel(Parcel.Serialization.BEAN)
data class ForecastDTO @ParcelConstructor constructor(val city: CityDTO?,
                                                      val list: List<WeatherDetailsDTO>)

@Parcel(Parcel.Serialization.BEAN)
data class CityDTO @ParcelConstructor constructor(val name: String?,
                                                  val coordDTO: CoordDTO?,
                                                  val country: String?,
                                                  val population: Double?)

@Parcel(Parcel.Serialization.BEAN)
data class SysDTO @ParcelConstructor constructor(val country: String?,
                                                 val sunrise: Long?,
                                                 val sunset: Long?)

@Parcel(Parcel.Serialization.BEAN)
data class UVDTO @ParcelConstructor constructor(val lat: String?,
                                                val lon: String?,
                                                val date: Long?,
                                                val value: Float?)