package denis.easyweather.app.data.remote

import denis.easyweather.app.data.remote.weatherModel.WeatherResponse
import io.reactivex.Single
import javax.inject.Inject


class RemoteWeatherDataSource @Inject constructor(private val remoteWeatherService: RemoteWeatherService) {

    fun requestWeatherForCityByCoordinates(latitude: String, longitude: String): Single<WeatherResponse> =
            remoteWeatherService.requestWeatherForCityByCoordinates(latitude, longitude)

    fun requestWeatherForCityByName(cityName: String): Single<WeatherResponse> =
            remoteWeatherService.requestWeatherForCityByName(cityName, "99d2ca8d62e1b1b71672f3e69b573710")

}