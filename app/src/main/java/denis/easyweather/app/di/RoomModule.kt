package denis.easyweather.app.di

import android.content.Context
import dagger.Module
import dagger.Provides
import denis.easyweather.app.data.room.RoomDataSource
import javax.inject.Singleton


@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideRoomCurrencyDataSource(context: Context) =
            RoomDataSource.getInstance(context)
}