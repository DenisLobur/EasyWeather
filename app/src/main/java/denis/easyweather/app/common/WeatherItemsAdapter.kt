package denis.easyweather.app.common

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import denis.easyweather.app.R
import denis.easyweather.app.model.ItemModel
import java.util.*

class WeatherItemsAdapter(private val context: Context) : RecyclerView.Adapter<WeatherItemsAdapter.ViewHolder>() {

    private val dataList: MutableList<ItemModel>?

    init {
        dataList = ArrayList<ItemModel>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false)
        val viewHolder = ViewHolder(view)

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.date!!.text = dataList!![position].date
        holder.condition!!.text = dataList[position].condition
        holder.dayTemp!!.text = dataList[position].dayTemp
        holder.nightTemp!!.text = dataList[position].nightTemp

        holder.itemView.setOnClickListener {
            val intent = Intent()
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    fun addItem(item: ItemModel) {
        dataList!!.add(item)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var date: TextView? = null
        internal var condition: TextView? = null
        internal var dayTemp: TextView? = null
        internal var nightTemp: TextView? = null
    }
}
