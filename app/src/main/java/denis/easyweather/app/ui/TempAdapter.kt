package denis.easyweather.app.ui

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import denis.easyweather.app.R
import kotlinx.android.synthetic.main.item_temp_bar.view.*

class TempAdapter(val context: Context, val timeToTempList: List<TimeToTemperature>) : RecyclerView.Adapter<TempAdapter.TimeToTempHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TimeToTempHolder {
        val rootView = LayoutInflater.from(context).inflate(R.layout.item_temp_bar, parent, false)

        return TimeToTempHolder(rootView)
    }

    override fun getItemCount(): Int {
        return timeToTempList.size
    }

    override fun onBindViewHolder(holder: TimeToTempHolder, p1: Int) {
        val item = timeToTempList.get(p1)
        holder.bind(item)
    }

    inner class TimeToTempHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: TimeToTemperature) {
            itemView.time_value.text = item.time
            itemView.temperature_bar_value.setBarValue(18 * item.temp)
            if (item.temp > 0) {
                itemView.temperature_value.setTextColor(Color.RED)
            } else if (item.temp < 0) {
                itemView.temperature_value.setTextColor(Color.BLUE)
            } else {
                itemView.temperature_value.setTextColor(Color.BLACK)
            }
            itemView.temperature_value.text = context.getString(R.string.temperature_graph_value, item.temp.toString())
        }
    }
}