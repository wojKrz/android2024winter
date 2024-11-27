package edu.programmingclasses2024winter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import edu.programmingclasses2024winter.FirstViewData.HasData
import edu.programmingclasses2024winter.FirstViewData.IsInProgress
import edu.programmingclasses2024winter.databinding.FragmentFirstBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class FirstFragment : Fragment() {

  private lateinit var binding: FragmentFirstBinding

  private val viewModel: FirstViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.d("Lifecycle", "OnCreate Fragment $savedInstanceState")
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentFirstBinding.inflate(inflater, container, false)

    binding.navButton.setOnClickListener {
      viewModel.makeNetworkCall()
    }
    binding.helloButton.setOnClickListener {
      findNavController().navigate(
        NavMainDirections.actionNavigateToThirdFragment()
      )
    }
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    viewModel.resultLiveData.observe(viewLifecycleOwner) { data ->
      binding.resultData = data
    }
  }


  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)
    Log.d("Lifecycle", "OnViewStateRestored $savedInstanceState")
  }
}
