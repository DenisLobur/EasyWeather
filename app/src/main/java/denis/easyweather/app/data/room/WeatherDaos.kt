package denis.easyweather.app.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import retrofit2.Call

@Dao
interface WeatherCitiesDao {

    @Insert
    fun insertAll(cities: List<CityEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(city: CityEntity)

    @Query(RoomConfig.SELECT_CITIES)
    fun getAllCities(): Call<List<CityEntity>>
}