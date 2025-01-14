package edu.programmingclasses2024winter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import edu.programmingclasses2024winter.cat.Cat
import edu.programmingclasses2024winter.cat.Tiger
import edu.programmingclasses2024winter.databinding.FragmentFirstBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

val COUNTER = intPreferencesKey("counter")

@AndroidEntryPoint
class FirstFragment : Fragment() {

  private val postsAdapter = PostListAdapter(
    emptyList(),
    ::onIsReadClick
  )
  private lateinit var binding: FragmentFirstBinding

  private val viewModel: FirstViewModel by viewModels()

  @Inject
  lateinit var cat: Cat

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

    val postsListLayoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

    binding.postsList.apply {
      adapter = postsAdapter
      layoutManager = postsListLayoutManager
    }

    binding.navButton.setOnClickListener {
      findNavController().navigate(R.id.actionNavigateToThirdFragment)
    }
    binding.helloButton.setOnClickListener {
      lifecycleScope.launch {
        incrementCounterInPrefs()
      }
    }
    return binding.root
  }

  private suspend fun incrementCounterInPrefs() {
    context?.myDataStore?.edit {
      val current = it[COUNTER] ?: 0
      it[COUNTER] = current.inc()
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    viewModel.resultLiveData.observe(viewLifecycleOwner) { data ->
      postsAdapter.posts = data
      postsAdapter.notifyDataSetChanged()
    }

    lifecycleScope.launch {
      context?.myDataStore?.data
        ?.map { it[COUNTER] ?: 0 }
        ?.collect {
          binding.counter.text = it.toString()
        }
    }
  }

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)
    Log.d("Lifecycle", "OnViewStateRestored $savedInstanceState")
  }

  private fun onIsReadClick(index: Int) {
    viewModel.toggleIsPostRead(index)
  }
}
