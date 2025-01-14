package edu.programmingclasses2024winter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import edu.programmingclasses2024winter.databinding.FragmentSecondBinding

@AndroidEntryPoint
class SecondFragment : Fragment() {

  private lateinit var binding: FragmentSecondBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentSecondBinding.inflate(inflater, container, false)
    binding.user = navArgs<SecondFragmentArgs>().value.passedText.run(::createUser)

    return binding.root
  }

  private fun createUser(userName: String): User =
    User(
      userName = userName,
      userId = "hsdf8nsdf0",
      age = 34
    )

}
