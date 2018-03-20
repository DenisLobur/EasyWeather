package denis.easyweather.app.dto

import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
data class WeatherDetailsDTO @ParcelConstructor constructor(val cityName: String,
                                                            val coord: CoordDTO?,
                                                            val main: MainDTO?,
                                                            val clouds: CloudsDTO?,
                                                            val wind: WindDTO?,
                                                            val weatherEntryList: List<WeatherEntryDTO>?)

@Parcel(Parcel.Serialization.BEAN)
data class MainDTO @ParcelConstructor constructor(val temp: Double?,
                                                  val pressure: Int?,
                                                  val humidity: Int?,
                                                  val tempMin: Double?,
                                                  val tempMax: Double?)

@Parcel(Parcel.Serialization.BEAN)
data class CloudsDTO @ParcelConstructor constructor(val all: Int?)

@Parcel(Parcel.Serialization.BEAN)
data class WindDTO @ParcelConstructor constructor(val speed: Double?, val deg: Int?)

@Parcel(Parcel.Serialization.BEAN)
data class CoordDTO @ParcelConstructor constructor(val longitude: Double?,
                                                   val latitude: Double?)

@Parcel(Parcel.Serialization.BEAN)
data class WeatherEntryDTO @ParcelConstructor constructor(val main: String?, val description: String?)
