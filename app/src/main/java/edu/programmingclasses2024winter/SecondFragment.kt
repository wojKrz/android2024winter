package edu.programmingclasses2024winter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import edu.programmingclasses2024winter.databinding.FragmentSecondBinding
import edu.programmingclasses2024winter.net.PostsApi
import javax.inject.Inject

@AndroidEntryPoint
class SecondFragment : Fragment() {

  @Inject
  lateinit var postApi: PostsApi

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View = FragmentSecondBinding.inflate(inflater, container, false)
    .apply(::setupView)
    .root

  private fun setupView(binding: FragmentSecondBinding) = with(binding) {
    resultText.text = navArgs<SecondFragmentArgs>().value.typedText
    navigateFurther.setOnClickListener {
      findNavController().navigate(R.id.actionGlobalNavigateToSecondScreen)
    }
  }
}
