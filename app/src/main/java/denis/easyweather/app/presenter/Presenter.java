package denis.easyweather.app.presenter;

/**
 * Created by denis on 12/23/15.
 */
public interface Presenter<V> {
    void attachView(V view);
    void detachView();
}
