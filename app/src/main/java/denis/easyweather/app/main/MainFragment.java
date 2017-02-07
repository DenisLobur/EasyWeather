package denis.easyweather.app.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import denis.easyweather.app.R;
import denis.easyweather.app.common.BaseFragment;
import denis.easyweather.app.model.CityModel;

/**
 * Created by denis on 9/19/16.
 */

public class MainFragment extends BaseFragment implements MainView {

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;
    @BindView(R.id.city_name)
    EditText cityName;
    @BindView(R.id.search_city)
    Button searchCity;
    @BindView(R.id.coordinates)
    TextView coordinates;
    @BindView(R.id.weather)
    TextView weather;
    @BindView(R.id.main)
    TextView main;
    @BindView(R.id.wind)
    TextView wind;
    @BindView(R.id.clouds)
    TextView clouds;
    @BindView(R.id.suntime)
    TextView suntime;
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
        //presenter.getWeatherByCity("London");
        presenter.getWeatherByCity(cityName.getText().toString());
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
    public void showWeatherRx(CityModel cityModel) {
        coordinates.setText("coordinates: " + cityModel.getCoord().getLat() + " " + cityModel.getCoord().getLon());
        weather.setText("weather: " + cityModel.getWeather().get(0).getDescription());
        main.setText("main: " + cityModel.getMain().getTemp()+ "");
        wind.setText("wind: " + cityModel.getWind().getSpeed() + " " + cityModel.getWind().getSpeed());
        clouds.setText("clouds: " + cityModel.getClouds().getAll());
        suntime.setText("suntime: " + cityModel.getSys().getSunrise() + " " + cityModel.getSys().getSunset());
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }
}
