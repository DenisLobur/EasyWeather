package denis.easyweather.app.inject.components;

import com.squareup.picasso.Picasso;

import dagger.Component;
import denis.easyweather.app.inject.modules.PicassoModule;
import denis.easyweather.app.inject.modules.RestApiModule;
import denis.easyweather.app.inject.scopes.ApplicationScope;
import denis.easyweather.app.net.WeatherService;

/**
 * Created by Denis on 03-Feb-17.
 */

@ApplicationScope
@Component(modules = {RestApiModule.class, PicassoModule.class})
public interface AppComponent {

    WeatherService getWeatherService();

    Picasso getPicasso();
}
