package denis.easyweather.app.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import denis.easyweather.app.data.remote.weatherModel.WeatherResponse
import denis.easyweather.app.network.Result
import denis.easyweather.app.repository.WeatherRepository
import kotlinx.coroutines.launch

interface WeatherVMContract {
  val onWeatherRequested: LiveData<WeatherResponse>

  fun getWeatherByCityName(cityName: String)
}

class WeatherVM(
  private val weatherRepository: WeatherRepository
) : WeatherVMContract, BaseViewModel() {

  private val weather = MutableLiveData<WeatherResponse>()
  override val onWeatherRequested: LiveData<WeatherResponse> = weather

  override fun getWeatherByCityName(cityName: String) {
    viewModelScope.launch {
      when (val result = weatherRepository.requestWeatherForCityByName()) {
        is Result.Success -> weather.postValue(result.data)
        is Result.Error.RequestError -> errorMessage.postValue(" Error during weather call")
      }
    }
  }

}