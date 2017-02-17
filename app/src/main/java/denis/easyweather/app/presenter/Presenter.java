package denis.easyweather.app.presenter;

import denis.easyweather.app.router.Router;

public interface Presenter<V> {
    void attachView(V view, Router router);
    void detachView();
}
