package denis.easyweather.app.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.Unbinder;
import denis.easyweather.app.R;
import denis.easyweather.app.common.BaseFragment;
import denis.easyweather.app.common.Util;
import denis.easyweather.app.model.CityModel;

public class MainFragment extends BaseFragment implements MainView {

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

    @OnEditorAction(R.id.city_name)
    public boolean inSearch(KeyEvent event) {
        if (event.getKeyCode() == event.KEYCODE_ENTER) {
            presenter.getWeatherByCity(cityName.getText().toString());
            return true;
        }
//TODO: make it work!
        return false;
    }

    @OnClick(R.id.search_city)
    public void onSearchClick() {
        Log.d("result", "click");
        Util.hideKeyboard(getContext(), getView());
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
                R.string.latitude, String.valueOf(cityModel.coord.lat)));
        longitude.setText(getActivity().getResources().getString(
                R.string.longitude, String.valueOf(cityModel.coord.lon)));
        temperature.setText(getActivity().getResources().getString(
                R.string.temperature, String.valueOf(Util.convertTemperature(cityModel.main.temp))));
        humidity.setText(getActivity().getResources().getString(
                R.string.humidity, String.valueOf(cityModel.main.humidity)));
        pressure.setText(getActivity().getResources().getString(
                R.string.pressure, String.valueOf(cityModel.main.pressure)));
        maxTemp.setText(getActivity().getResources().getString(
                R.string.max_temp, String.valueOf(Util.convertTemperature(cityModel.main.temp_max))));
        minTemp.setText(getActivity().getResources().getString(
                R.string.min_temp, String.valueOf(Util.convertTemperature(cityModel.main.temp_min))));
        wind.setText(getActivity().getResources().getString(
                R.string.wind_data, String.valueOf(cityModel.wind.speed), String.valueOf(cityModel.wind.deg)));
        clouds.setText(getActivity().getResources().getString(
                R.string.clouds, String.valueOf(cityModel.clouds.all)));
        sunrise.setText(getActivity().getResources().getString(
                R.string.sunrise, Util.formatDate(cityModel.sys.sunrise)));
        sunset.setText(getActivity().getResources().getString(
                R.string.sunset, Util.formatDate(cityModel.sys.sunset)));
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }
}
