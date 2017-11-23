package denis.easyweather.app.common

import android.os.Bundle
import android.support.v4.app.Fragment

import denis.easyweather.app.inject.components.ActivityComponent
import denis.easyweather.app.router.MainActivity
import denis.easyweather.app.router.Router

abstract class BaseFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        inject()
    }

    protected val router: Router?
        get() = (activity as MainActivity).router

    protected abstract fun inject()

    protected val activityComponent: ActivityComponent?
        get() = (activity as MainActivity).activityComponent
}
