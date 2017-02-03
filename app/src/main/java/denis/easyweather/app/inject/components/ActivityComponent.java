package denis.easyweather.app.inject.components;

import dagger.Component;
import denis.easyweather.app.inject.modules.MainActivityModule;
import denis.easyweather.app.inject.scopes.ActivityScope;
import denis.easyweather.app.net.WeatherService;

/**
 * Created by Denis on 03-Feb-17.
 */

@ActivityScope
@Component(modules = MainActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {

    WeatherService getWeatherService();
}
