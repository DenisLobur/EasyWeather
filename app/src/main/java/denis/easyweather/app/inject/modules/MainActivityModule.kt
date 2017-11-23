package denis.easyweather.app.inject.modules

import dagger.Module
import dagger.Provides
import denis.easyweather.app.inject.scopes.ActivityScope
import denis.easyweather.app.router.MainActivity

@Module
class MainActivityModule(private val mainActivity: MainActivity) {

    @Provides
    @ActivityScope
    fun mainActivity(): MainActivity {
        return mainActivity
    }
}
