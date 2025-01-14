package edu.programmingclasses2024winter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirdFragment : Fragment() {

  private val viewModel: ThirdViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view =inflater.inflate(R.layout.fragment_third, container, false)
    view.findViewById<ComposeView>(R.id.composeContainer).apply {
      setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
      setContent {
        // In Compose world
        MaterialTheme {
          Text("Hello Compose!")
        }
      }
    }

    return view.rootView
  }

  override fun onStart() {
    super.onStart()
    viewModel.toString()
  }
}
