package denis.easyweather.app.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import denis.easyweather.app.R;

/**
 * Created by denis on 1/16/16.
 */
public class DetailActivity extends AppCompatActivity {

    public static final String ARG_CITY = "city";
    public static final String ARG_DATE = "date";
    public static final String ARG_MIN = "min";
    public static final String ARG_MAX = "max";
    public static final String ARG_DESC = "description";
    @Bind(R.id.detail_city)
    TextView city;
    @Bind(R.id.detail_date)
    TextView date;
    @Bind(R.id.detail_min_temp)
    TextView min;
    @Bind(R.id.detail_max_temp)
    TextView max;
    @Bind(R.id.detail_description)
    TextView description;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail);

        ButterKnife.bind(this);
        Intent intent = getIntent();
        city.setText(intent.getStringExtra(ARG_CITY));
        date.setText(intent.getStringExtra(ARG_DATE));
        min.setText(intent.getStringExtra(ARG_MIN));
        max.setText(intent.getStringExtra(ARG_MAX));
        description.setText(intent.getStringExtra(ARG_DESC));
    }
}
