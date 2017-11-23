package denis.easyweather.app.inject.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import dagger.Module
import dagger.Provides
import denis.easyweather.app.inject.scopes.ApplicationScope
import denis.easyweather.app.net.WeatherService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = arrayOf(NetworkModule::class))
class RestApiModule {

    @Provides
    @ApplicationScope
    fun getRestApi(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }

    @Provides
    @ApplicationScope
    fun gson(): Gson {
        val gsonBuilder = GsonBuilder()
        //        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());
        return gsonBuilder.create()
    }

    @Provides
    @ApplicationScope
    fun retrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()
    }

    companion object {
        private val BASE_URL = "http://api.openweathermap.org/"
    }
}
