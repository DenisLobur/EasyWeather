package denis.easyweather.app.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import denis.easyweather.app.R
import java.util.*

//TODO: finish this
class SearchedAdapter(listener: Listener) : RecyclerView.Adapter<SearchedAdapter.ViewHolder>() {

    interface Listener {
        fun onCityClick(city: String)
    }

    private var listener = listener
    private val dataList: MutableList<String>?

    init {
        dataList = ArrayList<String>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): SearchedAdapter.ViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return ViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    override fun onBindViewHolder(p0: SearchedAdapter.ViewHolder, p1: Int) {

    }


    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
//        internal var cityName: TextView? =
    }
}