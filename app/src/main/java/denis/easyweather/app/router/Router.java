package denis.easyweather.app.router;

/**
 * Created by denis on 9/19/16.
 */

public interface Router {

    void goToScreen(ViewPort screen);

    void goToScreen(ViewPort.Type type);
}
