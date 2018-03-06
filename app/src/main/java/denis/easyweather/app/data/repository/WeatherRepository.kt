package denis.easyweather.app.data.repository

import denis.easyweather.app.data.room.CityEntity
import denis.easyweather.app.dto.WeatherDetailsDTO
import io.reactivex.Flowable
import io.reactivex.Single


interface WeatherRepository {

    fun getCities(): Flowable<List<CityEntity>>

    fun getWeather(cityName: String): Single<WeatherDetailsDTO>

    fun addCity(cityName: String)
}