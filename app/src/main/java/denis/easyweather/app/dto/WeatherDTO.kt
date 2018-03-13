package denis.easyweather.app.dto

import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
data class HourlyWeatherDTO @ParcelConstructor constructor(val timestamp: Long,
                                                           val temperature: Double)

@Parcel(Parcel.Serialization.BEAN)
data class WeatherDetailsDTO @ParcelConstructor constructor(val cityName: String,
                                                            val coord: CoordDTO?,
                                                            val main: MainDTO?,
                                                            val humidity: Double?,
                                                            val windSpeed: Double?,
                                                            val cloudsPercentage: Double?,
                                                            val hourlyWeatherList: ArrayList<HourlyWeatherDTO>?,
                                                            val hourlyWeatherStringFormatedHoursList: ArrayList<String>?)

@Parcel(Parcel.Serialization.BEAN)
data class MainDTO @ParcelConstructor constructor(val temp: Double?,
                                               val pressure: Int?,
                                               val humidity: Int?,
                                               val tempMin: Double?,
                                               val tempMax: Double?)

@Parcel(Parcel.Serialization.BEAN)
data class CoordDTO @ParcelConstructor constructor(val longitude: Double?,
                                                   val latitude: Double?)
