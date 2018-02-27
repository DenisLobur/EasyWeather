package denis.easyweather.app.data.remote

import denis.easyweather.app.data.remote.locationModel.LocationResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface RemoteGeocodingService {

    @GET("json")
    fun requestCityAddressByName(
            @Query("address") address: String
    ): Single<LocationResponse>
}