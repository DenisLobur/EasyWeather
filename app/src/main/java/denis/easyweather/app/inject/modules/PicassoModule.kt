package denis.easyweather.app.inject.modules

import android.content.Context

import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

import dagger.Module
import dagger.Provides
import denis.easyweather.app.inject.scopes.ApplicationScope
import okhttp3.OkHttpClient

@Module(includes = arrayOf(ContextModule::class, NetworkModule::class))
class PicassoModule {
    @Provides
    @ApplicationScope
    fun picasso(context: Context, okHttp3Downloader: OkHttp3Downloader): Picasso {
        return Picasso.Builder(context)
                .downloader(okHttp3Downloader)
                .build()
    }

    @Provides
    @ApplicationScope
    fun okHttp3Downloader(okHttpClient: OkHttpClient): OkHttp3Downloader {
        return OkHttp3Downloader(okHttpClient)
    }
}
