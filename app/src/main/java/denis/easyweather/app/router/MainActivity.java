package denis.easyweather.app.router;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import denis.easyweather.app.common.WeatherItemsAdapter;
import denis.easyweather.app.main.MainPresenter;
import denis.easyweather.app.model.ItemModel;

public class MainActivity extends AppCompatActivity {

    private MainPresenter presenter;
    private WeatherItemsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<ItemModel> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
