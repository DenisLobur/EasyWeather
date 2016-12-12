package denis.easyweather.app.router;

/**
 * Created by denis on 9/19/16.
 */

public interface Router {

    void goToScreen(Screen screen);

    void goToScreen(Screen.Type type);
}
