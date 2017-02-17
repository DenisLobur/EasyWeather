package denis.easyweather.app.inject.modules;

import dagger.Module;
import dagger.Provides;
import denis.easyweather.app.inject.scopes.ActivityScope;
import denis.easyweather.app.main.MainPresenter;
import denis.easyweather.app.net.WeatherService;

@Module
public class MainPresenterModule {
    @Provides
    @ActivityScope
    MainPresenter provideMainPresenter(WeatherService weatherService) {
        return new MainPresenter(weatherService);
    }
}
