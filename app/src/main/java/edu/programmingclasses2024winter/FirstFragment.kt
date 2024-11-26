package edu.programmingclasses2024winter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.programmingclasses2024winter.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

  private lateinit var binding: FragmentFirstBinding

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
      navigateToSecondFragment()
    }
    binding.helloButton.setOnClickListener {
      findNavController().navigate(
        NavMainDirections.actionNavigateToThirdFragment()
      )
    }
    return binding.root
  }

  private fun navigateToSecondFragment() {
    val action = binding.textInput.text.toString()
      .takeIf(String::isNotEmpty)
      ?.run(FirstFragmentDirections::actionNavigateToSecondFragment)
      ?: FirstFragmentDirections.actionNavigateToSecondFragment()

    findNavController().navigate(action)
  }

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)
    Log.d("Lifecycle", "OnViewStateRestored $savedInstanceState")
  }
}
