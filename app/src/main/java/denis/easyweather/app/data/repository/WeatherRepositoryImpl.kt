package denis.easyweather.app.data.repository

import denis.easyweather.app.data.remote.RemoteWeatherDataSource
import denis.easyweather.app.data.remote.locationModel.LocationResponse
import denis.easyweather.app.data.remote.weatherModel.WeatherResponse
import denis.easyweather.app.data.room.CityEntity
import denis.easyweather.app.data.room.RoomDataSource
import denis.easyweather.app.domain.dto.WeatherDetailsDTO
import denis.easyweather.app.utils.TransformersDTO
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
        private val remoteWeatherDataSource: RemoteWeatherDataSource,
        private val roomDataSource: RoomDataSource
) : WeatherRepository {

    override fun getWeather(cityName: String): Single<WeatherDetailsDTO> {

        return remoteWeatherDataSource.requestCityAddressByName(cityName)
                .flatMap({ locationResponse: LocationResponse ->
                    remoteWeatherDataSource.requestWeatherForCity(
                            locationResponse.results[0].geometry.location.lat.toString(),
                            locationResponse.results[0].geometry.location.lng.toString()
                    )
                            .map { weatherResponse: WeatherResponse ->
                                TransformersDTO.transformToWeatherDetailsDTO(
                                        locationResponse.results[0].formatted_address,
                                        weatherResponse
                                )
                            }
                })
                .retry(1)

    }


    override fun getCities(): Flowable<List<CityEntity>> {
        return roomDataSource.weatherSearchCityDao().getAllCities()
    }

    override fun addCity(cityName: String) {
        Completable.fromCallable { roomDataSource.weatherSearchCityDao().insertCity(CityEntity(cityName = cityName)) }.subscribeOn(Schedulers.io()).subscribe()
    }

}