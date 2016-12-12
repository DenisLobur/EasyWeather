package denis.easyweather.app.presenter;

import denis.easyweather.app.router.Router;

/**
 * Created by denis on 12/23/15.
 */
public interface Presenter<V> {
    void attachView(V view, Router router);
    void detachView();
}
