package denis.easyweather.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import denis.easyweather.app.databinding.FragmentMainWeatherBinding

class MainWeatherFragment : Fragment() {

  private lateinit var binding: FragmentMainWeatherBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentMainWeatherBinding.inflate(inflater, container, false).apply {
      lifecycleOwner = viewLifecycleOwner
    }

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    //TODO: VM calls here
  }
}