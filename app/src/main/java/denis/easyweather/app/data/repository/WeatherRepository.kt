package denis.easyweather.app.data.repository

import denis.easyweather.app.data.room.CityEntity
import denis.easyweather.app.dto.ForecastDTO
import denis.easyweather.app.dto.UVDTO
import denis.easyweather.app.dto.WeatherDetailsDTO
import io.reactivex.Flowable
import io.reactivex.Single


interface WeatherRepository {

    fun getCities(): Flowable<List<CityEntity>>

    fun getWeather(cityName: String): Single<WeatherDetailsDTO>

    fun getWeatherByCoord(latitude: Double, longitude: Double): Single<WeatherDetailsDTO>

    fun getFiveDaysForecast(cityName: String): Single<ForecastDTO>

    fun addCity(cityName: String)

    fun getUVData(lat: String, lon: String): Single<UVDTO>
}