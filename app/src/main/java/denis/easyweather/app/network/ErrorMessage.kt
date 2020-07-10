package denis.easyweather.app.network

import com.google.gson.annotations.SerializedName

data class ErrorMessage(
  @SerializedName("code") val errorCode: Int,
  @SerializedName("message") val errorDescription: String
)

fun ErrorMessage.toText() = "$errorCode:$errorDescription"
