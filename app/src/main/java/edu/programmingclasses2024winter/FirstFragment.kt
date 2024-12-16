package edu.programmingclasses2024winter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.programmingclasses2024winter.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
  private val viewModel: FirstViewModel by viewModels()
  private lateinit var binding: FragmentFirstBinding

  private val postsAdapter = PostsListAdapter(emptyList(), ::onIsReadClick)

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentFirstBinding.inflate(inflater, container, false)
    binding.button.setOnClickListener {
      viewModel.getPosts()
    }

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.listLayout.apply {
      adapter = postsAdapter
      layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    viewModel.resultLiveData.observe(viewLifecycleOwner) { data ->
      postsAdapter.data = data
      postsAdapter.notifyDataSetChanged()
    }

    viewModel.errorLiveData.observe(viewLifecycleOwner) { error ->
      Toast.makeText(requireContext(), error.text, Toast.LENGTH_SHORT).show()
    }
  }

  private fun onIsReadClick(index: Int) {
    viewModel.toggleIsPostRead(postsAdapter.data[index])
  }
}
