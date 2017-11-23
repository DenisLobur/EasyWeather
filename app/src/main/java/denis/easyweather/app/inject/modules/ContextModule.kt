package denis.easyweather.app.inject.modules

import android.content.Context

import dagger.Module
import dagger.Provides
import denis.easyweather.app.inject.scopes.ApplicationScope

@Module
class ContextModule(private val context: Context) {

    @Provides
    @ApplicationScope
    fun context(): Context {
        return context
    }
}
