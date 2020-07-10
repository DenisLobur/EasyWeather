package denis.easyweather.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import denis.easyweather.app.R
import denis.easyweather.app.databinding.ActivityMainNewBinding

class MainActivityNew : AppCompatActivity(), LifecycleOwner {

  private lateinit var binding: ActivityMainNewBinding
  private val navController by lazy { findNavController(R.id.navHostFragment) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding =
      DataBindingUtil.setContentView<ActivityMainNewBinding>(this, R.layout.activity_main_new)
        .apply {
          lifecycleOwner = this@MainActivityNew
        }
  }
}