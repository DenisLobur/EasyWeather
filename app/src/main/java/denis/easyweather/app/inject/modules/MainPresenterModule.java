package denis.easyweather.app.inject.modules;

import dagger.Module;
import dagger.Provides;
import denis.easyweather.app.inject.scopes.ActivityScope;
import denis.easyweather.app.main.MainPresenter;
import denis.easyweather.app.net.WeatherService;

/**
 * Created by Denis on 06-Feb-17.
 */
@Module
public class MainPresenterModule {
    @Provides
    @ActivityScope
    MainPresenter provideMainPresenter(WeatherService weatherService) {
        return new MainPresenter(weatherService);
    }
}
