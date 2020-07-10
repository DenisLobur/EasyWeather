package denis.easyweather.app.network

import retrofit2.Response
import java.net.SocketTimeoutException

class SimpleRequestHandler<ResponseType, ResultType : Any>(
  private val networkManager: NetworkManager,
  private val errorParser: ErrorParser,
  private val onRequest: suspend () -> Response<ResponseType>,
  private val onResult: suspend (responseBody: ResponseType) -> Result<ResultType>,
  private val withoutBody: Boolean = false
) {

  suspend fun request(): Result<ResultType> {
    if (!networkManager.isOnline()) return Result.Error.NoInternetConnection

    val response: Response<ResponseType>

    try {
      response = onRequest.invoke()
    } catch (e: SocketTimeoutException) {

      return Result.Error.NoInternetConnection
    } catch (e: Exception) {

      return Result.Error.GenericError(e)
    }

    if (response.isSuccessful) {
      if (withoutBody) {
        return onResult.invoke(response.body()!!)
      }

      val responseBody = response.body() ?: return Result.Error.GenericError(
        IllegalArgumentException("empty body")
      )

      return onResult.invoke(responseBody)
    } else {

      return errorParser.handleError(response)
    }
  }
}