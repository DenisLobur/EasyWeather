package denis.easyweather.app.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import denis.easyweather.app.R
import denis.easyweather.app.common.BaseFragment

class DetailFragment : BaseFragment() {

    @BindView(R.id.detail_city)
    internal var city: TextView? = null
    @BindView(R.id.detail_date)
    internal var date: TextView? = null
    @BindView(R.id.detail_min_temp)
    internal var min: TextView? = null
    @BindView(R.id.detail_max_temp)
    internal var max: TextView? = null
    @BindView(R.id.detail_description)
    internal var description: TextView? = null

    private var unbinder: Unbinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_detail, container, false)
        unbinder = ButterKnife.bind(this, rootView)

        return rootView
    }

    override fun onDestroyView() {
        unbinder!!.unbind()
        super.onDestroyView()
    }

    override fun inject() {
        activityComponent?.inject(this)
    }
}
