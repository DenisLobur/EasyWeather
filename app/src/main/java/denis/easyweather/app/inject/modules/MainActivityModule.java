package denis.easyweather.app.inject.modules;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import denis.easyweather.app.inject.scopes.ActivityScope;
import denis.easyweather.app.router.MainActivity;

/**
 * Created by Denis on 03-Feb-17.
 */

@Module
public class MainActivityModule {
    private final MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @ActivityScope
    public MainActivity mainActivity() {
        return mainActivity;
    }
}
