package denis.easyweather.app.network.response

import com.google.gson.annotations.SerializedName

data class CloudsDTO(
  @SerializedName("all") val all: Int
)