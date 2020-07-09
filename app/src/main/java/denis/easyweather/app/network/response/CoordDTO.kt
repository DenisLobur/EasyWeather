package denis.easyweather.app.network.response

import com.google.gson.annotations.SerializedName

data class CoordDTO(
  @SerializedName("lon") val lon: Double,
  @SerializedName("lat") val lat: Double
)
