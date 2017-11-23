package denis.easyweather.app.router

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

import com.annimon.stream.Optional

import java.util.HashMap

import denis.easyweather.app.R
import denis.easyweather.app.detail.DetailFragment
import denis.easyweather.app.main.MainFragment
import rx.functions.Action1

class MainActivityRouter(private val activity: AppCompatActivity) : Router {
    private val screenHandlers = initScreenHandler()

    override fun goToScreen(screen: Screen) {
        screenHandlers[screen.type]?.call(screen)
    }

    override fun goToScreen(type: Screen.Type) {
        goToScreen(Screen.fromType(type))
    }

    private fun initScreenHandler(): Map<Screen.Type, Action1<Screen>> {
        val handlers = HashMap<Screen.Type, Action1<Screen>>()
        handlers.put(Screen.Type.MAIN, createFragmentScreenHandler(MainFragment::class.java))
        handlers.put(Screen.Type.DETAIL, createFragmentScreenHandler(DetailFragment::class.java))

        return handlers
    }

    private fun goToFragment(screen: Screen, fragmentClass: Class<out Fragment>) {
        val tag = screen.type.name
        val fragmentManager = activity.supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        // find fragment by tag or create new one if it is absent
        val fragment = Optional
                .ofNullable(fragmentManager.findFragmentByTag(tag))
                .orElse(createFragment(fragmentClass))
        addToFragmentArguments(screen, fragment)
        transaction.addToBackStack(null)
        transaction.replace(R.id.main_frame, fragment, tag).commit()
    }

    private fun addToFragmentArguments(screen: Screen, fragment: Fragment) {
        val params = screen.arguments
        val bundle = fragment.arguments
        if (bundle == null) {
            fragment.arguments = params
        } else {
            fragment.arguments.putAll(params)
        }
    }

    private fun createFragment(fragmentClass: Class<out Fragment>): Fragment {
        try {
            return fragmentClass.newInstance()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }

    private fun createFragmentScreenHandler(fragmentClass: Class<out Fragment>): Action1<Screen> {
//        return { goToFragment(it, fragmentClass) }
    return Action1 {  }
    }
}
