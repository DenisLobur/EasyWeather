package denis.easyweather.app.inject.components;

import dagger.Component;
import denis.easyweather.app.detail.DetailFragment;
import denis.easyweather.app.inject.modules.MainActivityModule;
import denis.easyweather.app.inject.scopes.ActivityScope;
import denis.easyweather.app.main.MainFragment;
import denis.easyweather.app.net.WeatherService;

@ActivityScope
@Component(modules = MainActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {

    WeatherService getWeatherService();

    void inject(MainFragment mainFragment);

    void inject(DetailFragment detailFragment);
}
