package denis.easyweather.app.router

interface Router {

    fun goToScreen(screen: Screen)

    fun goToScreen(type: Screen.Type)
}
