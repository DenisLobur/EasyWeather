package denis.easyweather.app.presenter

import denis.easyweather.app.router.Router

interface Presenter<V> {

    fun attachView(view: V, router: Router?)

    fun detachView()
}
