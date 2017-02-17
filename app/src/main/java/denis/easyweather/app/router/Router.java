package denis.easyweather.app.router;

public interface Router {

    void goToScreen(Screen screen);

    void goToScreen(Screen.Type type);
}
