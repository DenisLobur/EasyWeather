package denis.easyweather.app.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import denis.easyweather.app.R;
import denis.easyweather.app.common.BaseFragment;

/**
 * Created by denis on 9/19/16.
 */

public class DetailFragment extends BaseFragment {

    @BindView(R.id.detail_city)
    TextView city;
    @BindView(R.id.detail_date)
    TextView date;
    @BindView(R.id.detail_min_temp)
    TextView min;
    @BindView(R.id.detail_max_temp)
    TextView max;
    @BindView(R.id.detail_description)
    TextView description;

    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    protected void inject() {
        //getActivityComponent().inject(this);
    }
}
