package edu.programmingclasses2024winter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import edu.programmingclasses2024winter.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val binding = FragmentFirstBinding.inflate(inflater, container, false)
    binding.button.setOnClickListener {
      val argument = binding.textInput.text.toString()

      val action = FirstFragmentDirections.actionNavigateToSecondScreen(typedText = argument)
      findNavController().navigate(action)
    }
    return binding.root
  }
}
