package denis.easyweather.app.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.Unbinder;
import denis.easyweather.app.R;
import denis.easyweather.app.common.BaseFragment;

/**
 * Created by denis on 9/19/16.
 */

public class MainFragment extends BaseFragment implements MainView {

    @BindView(R.id.main_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.main_toolbar)
    Toolbar toolbar;
    @BindView(R.id.city_name)
    EditText cityName;
    @BindView(R.id.search_city)
    Button searchCity;
    private Unbinder unbinder;

    @Inject
    MainPresenter presenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this, getRouter());
    }

    @Override
    public void onStop() {
        presenter.detachView();
        super.onStop();
    }

    @OnClick(R.id.search_city)
    public void onSearchClick() {
        Log.d("result", "click");
        presenter.runRequestRx("London");
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void showTodayWeather(String[] weatherData) {

    }

    @Override
    public void showWeatherRx(String s) {

    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }
}
