package denis.easyweather.app.data.repository

import denis.easyweather.app.data.room.CityEntity
import denis.easyweather.app.dto.ForecastDTO
import denis.easyweather.app.dto.UVDTO
import denis.easyweather.app.dto.WeatherDetailsDTO
import retrofit2.Call

interface WeatherRepository {

    fun getCities(): Call<List<CityEntity>>?

    fun getWeather(cityName: String): Call<WeatherDetailsDTO>?

    fun getWeatherByCoord(latitude: Double, longitude: Double): Call<WeatherDetailsDTO>?

    fun getFiveDaysForecast(cityName: String): Call<ForecastDTO>?

    fun addCity(cityName: String)

    fun getUVData(lat: String, lon: String): Call<UVDTO>?
}