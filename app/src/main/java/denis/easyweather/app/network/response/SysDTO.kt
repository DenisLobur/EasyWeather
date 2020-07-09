package denis.easyweather.app.network.response

import com.google.gson.annotations.SerializedName

data class SysDTO(
  @SerializedName("country") val country: String,
  @SerializedName("sunrise") val sunrise: Long,
  @SerializedName("sunset") val sunset: Long
)