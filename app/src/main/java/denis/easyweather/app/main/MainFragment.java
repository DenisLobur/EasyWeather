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
    @BindView(R.id.latitude)
    TextView latitude;
    @BindView(R.id.longitude)
    TextView longitude;
    @BindView(R.id.temp)
    TextView temperature;
    @BindView(R.id.humidity)
    TextView humidity;
    @BindView(R.id.pressure)
    TextView pressure;
    @BindView(R.id.max_temp)
    TextView maxTemp;
    @BindView(R.id.min_temp)
    TextView minTemp;
    @BindView(R.id.main)
    TextView main;
    @BindView(R.id.wind_data)
    TextView wind;
    @BindView(R.id.clouds)
    TextView clouds;
    @BindView(R.id.sunrise)
    TextView sunrise;
    @BindView(R.id.sunset)
    TextView sunset;
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
        latitude.setText(getActivity().getResources().getString(
                R.string.latitude, String.valueOf(cityModel.getCoord().getLat())));
        longitude.setText(getActivity().getResources().getString(
                R.string.longitude, String.valueOf(cityModel.getCoord().getLon())));
        temperature.setText(getActivity().getResources().getString(
                R.string.temperature, String.valueOf(cityModel.getWeatherData().getTemp())));
        humidity.setText(getActivity().getResources().getString(
                R.string.humidity, String.valueOf(cityModel.getWeatherData().getHumidity())));
        pressure.setText(getActivity().getResources().getString(
                R.string.pressure, String.valueOf(cityModel.getWeatherData().getPressure())));
        maxTemp.setText(getActivity().getResources().getString(
                R.string.max_temp, String.valueOf(cityModel.getWeatherData().getTempMax())));
        minTemp.setText(getActivity().getResources().getString(
                R.string.min_temp, String.valueOf(cityModel.getWeatherData().getTempMin())));
        wind.setText(getActivity().getResources().getString(
                R.string.wind_data, String.valueOf(cityModel.getWind().getSpeed()), String.valueOf(cityModel.getWind().getDeg())));
        clouds.setText(getActivity().getResources().getString(
                R.string.clouds, String.valueOf(cityModel.getClouds().getAll())));
        sunrise.setText(getActivity().getResources().getString(
                R.string.sunrise, String.valueOf(cityModel.getSys().getSunrise())));
        sunset.setText(getActivity().getResources().getString(
                R.string.sunset, String.valueOf(cityModel.getSys().getSunset())));
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }
}
