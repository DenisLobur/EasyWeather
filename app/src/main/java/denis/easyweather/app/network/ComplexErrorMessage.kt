package denis.easyweather.app.network

import com.google.gson.annotations.SerializedName

data class ComplexErrorMessage<T>(
  @SerializedName("code") val errorCode: Int,
  @SerializedName("message") val errorDescription: String,
  @SerializedName("data") val errorData: T? = null
)