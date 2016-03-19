package denis.easyweather.app.common;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import denis.easyweather.app.R;
import denis.easyweather.app.model.ItemModel;
import denis.easyweather.app.view.DetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by denis on 12/23/15.
 */
public class WeatherItemsAdapter extends RecyclerView.Adapter<WeatherItemsAdapter.ViewHolder> {

    private List<ItemModel> dataList;
    private Context context;

    public WeatherItemsAdapter(Context context) {
//        this.dataList = dataList;
        dataList = new ArrayList<>();
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.date.setText(dataList.get(position).getDate());
        holder.condition.setText(dataList.get(position).getCondition());
        holder.dayTemp.setText(dataList.get(position).getDayTemp());
        holder.nightTemp.setText(dataList.get(position).getNightTemp());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, DetailActivity.class);
                intent.putExtra(DetailActivity.ARG_CITY, "city");
                intent.putExtra(DetailActivity.ARG_DATE, dataList.get(position).getDate());
                intent.putExtra(DetailActivity.ARG_MIN, dataList.get(position).getDayTemp());
                intent.putExtra(DetailActivity.ARG_MAX, dataList.get(position).getNightTemp());
                intent.putExtra(DetailActivity.ARG_DESC, dataList.get(position).getCondition());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

    public void addItem(ItemModel item) {
        dataList.add(item);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_upper_text)
        TextView date;
        @Bind(R.id.item_lower_text)
        TextView condition;
        @Bind(R.id.item_upper_degree)
        TextView dayTemp;
        @Bind(R.id.item_lower_degree)
        TextView nightTemp;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
