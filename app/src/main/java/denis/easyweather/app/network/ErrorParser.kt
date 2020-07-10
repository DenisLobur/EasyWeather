package denis.easyweather.app.network

import com.google.gson.Gson
import denis.easyweather.app.utils.genericType
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

class ErrorParser(val gson: Gson) {

  fun getErrorMessage(rawBody: String): ErrorMessage = gson.fromJson(rawBody, ErrorMessage::class.java)

  fun hasComplexData(code: Int) = COMPLEX_CODE_ENTITY_MAP.containsKey(code)

  fun <T> getComplexErrorMessage(rawBody: String, code: Int): ComplexErrorMessage<T> {
    return gson.fromJson(rawBody, COMPLEX_CODE_ENTITY_MAP[code] ?: COMPLEX_CODE_ENTITY_MAP[-1])
  }

  private fun parseOldErrorResponse(rawResponse: String?): Result.Error {
    if (rawResponse.isNullOrEmpty()) {
      return Result.Error.GenericError(
        IllegalArgumentException("empty error body")
      )
    } else if (!isJSON(rawResponse)) {
      return Result.Error.RequestError(
        ErrorMessage(404,
        "Not valid JSON, most likely it's XML"
        )
      )
    }

    return Result.Error.RequestError(
      ErrorMessage(
        404,
        ""
      )
    )
  }

  fun <T> handleError(response: Response<T>): Result.Error {
    val code = response.code()

    return if (code in 500..599) {
      Result.Error.ServerError(code)
    } else {
      return parseOldErrorResponse(response.errorBody()?.string())
    }
  }

  private fun isJSON(rawString: String): Boolean {
    try {
      JSONObject(rawString)
    } catch (ex: JSONException) {
      try {
        JSONArray(rawString)
      } catch (ex1: JSONException) {
        return false
      }
    }
    return true
  }

  companion object {
    /**
     * You must register any complex error response in this map, where key is inner error code
     * and value is generic class metadata for Gson
     */
    val COMPLEX_CODE_ENTITY_MAP = mapOf(
      // we always can deserialize json as string
      -1 to genericType<ComplexErrorMessage<String>>()
    )
  }

}
