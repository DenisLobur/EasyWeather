package denis.easyweather.app.network.response

import com.google.gson.annotations.SerializedName
import denis.easyweather.app.dto.*

data class WeatherResponseDTO(
  @SerializedName("coord") val coord: CoordDTO,
  @SerializedName("weather") val weather: List<WeatherDTO>,
  @SerializedName("main") val main: MainDTO,
  @SerializedName("visibility") val visibility: Int?,
  @SerializedName("wind") val wind: WindDTO,
  @SerializedName("clouds") val clouds: CloudsDTO,
  @SerializedName("dt") val dt: Long,
  @SerializedName("sys") val sys: SysDTO,
  @SerializedName("id") val id: Int,
  @SerializedName("name") val name: String
)