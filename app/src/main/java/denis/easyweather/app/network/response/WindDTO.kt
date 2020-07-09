package denis.easyweather.app.network.response

import com.google.gson.annotations.SerializedName

data class WindDTO(
  @SerializedName("speed") val speed: Double,
  @SerializedName("deg") val deg: Int
)