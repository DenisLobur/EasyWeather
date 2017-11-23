package denis.easyweather.app.router

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView

import denis.easyweather.app.R
import denis.easyweather.app.WeatherApplication
import denis.easyweather.app.common.WeatherItemsAdapter
import denis.easyweather.app.inject.components.ActivityComponent
import denis.easyweather.app.inject.components.DaggerActivityComponent
import denis.easyweather.app.inject.modules.MainActivityModule
import denis.easyweather.app.model.ItemModel

class MainActivity : AppCompatActivity() {

    val adapter: WeatherItemsAdapter? = null
    val layoutManager: RecyclerView.LayoutManager? = null
    val dataList: List<ItemModel>? = null
    var router: MainActivityRouter? = null
    var activityComponent: ActivityComponent? = null
        private set


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = DaggerActivityComponent.builder()
                .mainActivityModule(MainActivityModule(this))
                .appComponent(WeatherApplication.get(this).appComponent())
                .build()

        router = MainActivityRouter(this)
        setContentView(R.layout.activity_main)
        navigateMainScreen()
    }

    fun getRouter(): Router? = router

    private fun navigateMainScreen() {
        router!!.goToScreen(Screen.fromType(Screen.Type.MAIN))
    }
}
