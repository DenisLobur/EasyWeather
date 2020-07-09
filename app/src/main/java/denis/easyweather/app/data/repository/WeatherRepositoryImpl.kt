package denis.easyweather.app.data.repository

import denis.easyweather.app.data.remote.RemoteWeatherDataSource
import denis.easyweather.app.data.remote.weatherModel.ForecastResponse
import denis.easyweather.app.data.remote.weatherModel.UVResponse
import denis.easyweather.app.data.remote.weatherModel.WeatherResponse
import denis.easyweather.app.dto.CityDTO
import denis.easyweather.app.dto.ForecastDTO
import denis.easyweather.app.dto.UVDTO
import denis.easyweather.app.dto.WeatherDetailsDTO
import denis.easyweather.app.utils.TransformersDTO
import retrofit2.Call

class WeatherRepositoryImpl constructor(
        private val remoteWeatherDataSource: RemoteWeatherDataSource
) : WeatherRepository {

    override fun getWeather(cityName: String): Call<WeatherDetailsDTO>? {

//        return remoteWeatherDataSource.requestWeatherForCityByName(cityName)
//                .map { weatherResponse: WeatherResponse ->
//                    TransformersDTO.transformToWeatherDetailsDTO(cityName, weatherResponse)
//                }
      return null
    }

    override fun getWeatherByCoord(latitude: Double, longitude: Double): Call<WeatherDetailsDTO>? {
//        return remoteWeatherDataSource.requestWeatherForCityByCoordinates(latitude.toString(), longitude.toString())
//                .map { weatherResponse: WeatherResponse ->
//                    TransformersDTO.transformToWeatherDetailsDTO("", weatherResponse)
//                }
      return null
    }

    override fun getFiveDaysForecast(cityName: String): Call<ForecastDTO>? {
//        return remoteWeatherDataSource.requestFiveDaysForecastForCityByName(cityName)
//                .map { forecastREsponse: ForecastResponse ->
//                    TransformersDTO.transformToForecastDetailDTO(cityName, forecastREsponse)
//                }
      return null

    }

    override fun getCities(): Call<List<CityDTO>>? {
        //return roomDataSource.weatherSearchCityDao().getAllCities()
      return null
    }

    override fun addCity(cityName: String) {
        //Call.fromCallable { roomDataSource.weatherSearchCityDao().insertCity(CityEntity(cityName = cityName)) }.subscribeOn(Schedulers.io()).subscribe()
    }

    override fun getUVData(lat: String, lon: String): Call<UVDTO>? {
//        return remoteWeatherDataSource.requestUVDataByCoordinates(lat, lon)
//                .map { uvResponse: UVResponse ->
//                    TransformersDTO.transformUVDTO(uvResponse)
//                }

      return null
    }
}