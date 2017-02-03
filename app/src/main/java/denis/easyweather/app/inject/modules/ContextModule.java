package denis.easyweather.app.inject.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import denis.easyweather.app.inject.scopes.ApplicationScope;

/**
 * Created by Denis on 03-Feb-17.
 */

@Module
public class ContextModule {
    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    public Context context(){
        return context;
    }
}
