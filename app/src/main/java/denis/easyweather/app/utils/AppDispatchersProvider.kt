package denis.easyweather.app.utils

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class AppDispatchersProvider constructor(
  val bgContext: CoroutineContext = Dispatchers.IO,
  val uiContext: CoroutineContext = Dispatchers.Main
)