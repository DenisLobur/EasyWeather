package denis.easyweather.app.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import denis.easyweather.app.inject.components.ActivityComponent;
import denis.easyweather.app.router.MainActivity;
import denis.easyweather.app.router.Router;

/**
 * Created by Denis on 06-Feb-17.
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inject();
    }

    protected Router getRouter() {
        return ((MainActivity) getActivity()).getRouter();
    }

    protected abstract void inject();

    protected ActivityComponent getActivityComponent() {
        return ((MainActivity)getActivity()).getActivityComponent();
    }
}
