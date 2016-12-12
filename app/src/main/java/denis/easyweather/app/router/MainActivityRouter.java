package denis.easyweather.app.router;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.annimon.stream.Optional;

import java.util.HashMap;
import java.util.Map;

import denis.easyweather.app.R;
import denis.easyweather.app.detail.DetailFragment;
import denis.easyweather.app.main.MainFragment;
import rx.functions.Action1;

/**
 * Created by denis on 9/19/16.
 */

public class MainActivityRouter implements Router {

    private AppCompatActivity activity;
    private Map<Screen.Type, Action1<Screen>> screenHandlers = initScreenHandler();

    public MainActivityRouter(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void goToScreen(Screen screen) {
        screenHandlers.get(screen.type).call(screen);
    }

    @Override
    public void goToScreen(Screen.Type type) {
        goToScreen(Screen.fromType(type));
    }

    private Map<Screen.Type, Action1<Screen>> initScreenHandler() {
        Map<Screen.Type, Action1<Screen>> handlers = new HashMap<>();
        handlers.put(Screen.Type.MAIN, createFragmentScreenHandler(MainFragment.class));
        handlers.put(Screen.Type.DETAIL, createFragmentScreenHandler(DetailFragment.class));

        return handlers;
    }

    private void goToFragment(Screen screen, Class<? extends Fragment> fragmentClass) {
        String tag = screen.type.name();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // find fragment by tag or create new one if it is absent
        Fragment fragment = Optional
                .ofNullable(fragmentManager.findFragmentByTag(tag))
                .orElse(createFragment(fragmentClass));
        addToFragmentArguments(screen, fragment);
        transaction.addToBackStack(null);
        transaction.replace(R.id.content_frame, fragment, tag).commit();
    }

    private void addToFragmentArguments(Screen screen, Fragment fragment){
        Bundle params = screen.arguments;
        Bundle bundle = fragment.getArguments();
        if(bundle == null) {
            fragment.setArguments(params);
        } else {
            fragment.getArguments().putAll(params);
        }
    }

    private Fragment createFragment(Class<? extends Fragment> fragmentClass) {
        try {
            return fragmentClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Action1<Screen> createFragmentScreenHandler(Class<? extends Fragment> fragmentClass) {
        return screen -> goToFragment(screen, fragmentClass);
    }
}
