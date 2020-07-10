package denis.easyweather.app.ui.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import denis.easyweather.app.network.Result
import denis.easyweather.app.network.toText
import timber.log.Timber

interface BaseVMContract {

  val onErrorMessage: MutableLiveData<String>
  val onNoInternetConnection: MutableLiveData<String>
  val onServerError: MutableLiveData<String>
  val isLoading: ObservableBoolean
}

open class BaseViewModel : ViewModel(),
  BaseVMContract {

  protected val errorMessage = MutableLiveData<String>()
  protected val noInternetConnection = MutableLiveData<String>()
  protected val serverError = MutableLiveData<String>()

  override val onErrorMessage = errorMessage
  override val isLoading = ObservableBoolean(false)
  override val onNoInternetConnection = noInternetConnection
  override val onServerError = serverError

  protected fun handleDefaultErrors(error: Result.Error, tag: String) {
    when (error) {
      is Result.Error.RequestError -> {
        Timber.w("Request error: ${error.errorMessage.toText()}")
        errorMessage.postValue(error.errorMessage.toText())
      }
      is Result.Error.GenericError -> {
        Timber.w(error.exception)
        errorMessage.postValue(error.exception.message)
      }
      is Result.Error.NoInternetConnection -> noInternetConnection.postValue(tag)
      is Result.Error.ServerError -> serverError.postValue(tag)
    }
  }
}