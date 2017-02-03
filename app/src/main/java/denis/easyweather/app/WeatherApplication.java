package denis.easyweather.app;

import android.app.Activity;
import android.app.Application;

import denis.easyweather.app.inject.components.AppComponent;
import denis.easyweather.app.inject.components.DaggerAppComponent;
import denis.easyweather.app.inject.modules.ContextModule;
import timber.log.Timber;

/**
 * Created by Denis on 03-Feb-17.
 */

public class WeatherApplication extends Application {

    private AppComponent appComponent;

    public static WeatherApplication get(Activity activity) {
        return (WeatherApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        // Only dependency with constructor is required
        appComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this)) // Only dependency with constructor is required
                .build();
    }

    public AppComponent appComponent() {
        return appComponent;
    }
}
