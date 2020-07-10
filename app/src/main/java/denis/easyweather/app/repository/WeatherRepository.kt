package denis.easyweather.app.repository

import denis.easyweather.app.data.remote.Api
import denis.easyweather.app.data.remote.weatherModel.WeatherResponse
import denis.easyweather.app.network.ErrorParser
import denis.easyweather.app.network.NetworkManager
import denis.easyweather.app.network.Result
import denis.easyweather.app.network.SimpleRequestHandler
import denis.easyweather.app.repository.converter.WeatherConverter
import denis.easyweather.app.utils.AppDispatchersProvider
import kotlinx.coroutines.withContext

interface WeatherRepository : Repository {

  suspend fun requestWeatherForCityByName(): Result<WeatherResponse>

}

class WeatherRepositoryImpl(
  private val api: Api,
  private val dispatchers: AppDispatchersProvider,
  private val errorParser: ErrorParser,
  private val networkManager: NetworkManager
) : WeatherRepository {

  override suspend fun requestWeatherForCityByName() = withContext(dispatchers.bgContext) {
    SimpleRequestHandler(
      networkManager = networkManager,
      errorParser = errorParser,
      onRequest = { api.requestWeatherForCityByName("", "", "") },
      onResult = {
        val weather = WeatherConverter().convert(it)
        denis.easyweather.app.network.Result.Success(weather)
      }
    ).request()
  }

}