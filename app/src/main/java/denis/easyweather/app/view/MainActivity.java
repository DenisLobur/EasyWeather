package denis.easyweather.app.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import denis.easyweather.app.R;
import denis.easyweather.app.common.WeatherItemsAdapter;
import denis.easyweather.app.model.ItemModel;
import denis.easyweather.app.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainView {

    @Bind(R.id.weather_list)
    RecyclerView weatherList;

    @Bind(R.id.city_id)
    EditText city;

    @Bind(R.id.run)
    Button runBtn;

    private MainPresenter presenter;
    private WeatherItemsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<ItemModel> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MainPresenter();
        presenter.attachView(this);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        layoutManager = new LinearLayoutManager(this);
        weatherList.setLayoutManager(layoutManager);
        //setData();
        adapter = new WeatherItemsAdapter(this);
        //weatherList.setAdapter(adapter);

//        runBtn.setOnClickListener(v -> presenter.runRequest(city.getText().toString()));
        runBtn.setOnClickListener(v -> presenter.runRequestRx(city.getText().toString()));
    }

    private void setData(){
        dataList = new ArrayList<>();
        dataList.add(new ItemModel("Today", "Clear", "18", "11"));
        dataList.add(new ItemModel("Tomorrow", "Fog", "13", "16"));
        dataList.add(new ItemModel("After Tomorrow", "Rain", "25", "15"));
        dataList.add(new ItemModel("11/12/2015", "Sunny", "12", "14"));
        dataList.add(new ItemModel("12/12/2015", "Mist", "13", "13"));
        dataList.add(new ItemModel("13/12/2015", "Fog", "44", "12"));

    }

    @Override
    public void showTodayWeather(String [] weatherData) {
        String token = "//";
        String singleData;
        String [] s;
        dataList = new ArrayList<>();
        for (int i = 0; i < weatherData.length; i++) {
            singleData = weatherData[i];
            s = singleData.split(token);
            adapter.addItem(new ItemModel(s[0], s[3], s[1], s[2]));
        }

        weatherList.setAdapter(adapter);
    }

    @Override
    public void showWeatherRx(String s) {
        String ss = s;
    }
}
