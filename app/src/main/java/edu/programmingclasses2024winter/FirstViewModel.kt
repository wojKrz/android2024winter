package edu.programmingclasses2024winter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.programmingclasses2024winter.usecases.DownloadPostsUseCase
import edu.programmingclasses2024winter.usecases.ToggleIsPostReadUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
  private val downloadPostsUseCase: DownloadPostsUseCase,
  private val toggleIsPostReadUseCase: ToggleIsPostReadUseCase,
  private val repository: PostRepository
) : ViewModel() {

  private val _resultLiveData = MutableLiveData<List<Post>>()
  val resultLiveData: LiveData<List<Post>> = _resultLiveData

  init {
    viewModelScope.launch {
      repository.getAllPosts()
        .collect {
          _resultLiveData.value = it
        }
    }
  }

  fun makeNetworkCall() {
    viewModelScope.launch(Dispatchers.IO) {
      downloadPostsUseCase()
    }
  }

  fun toggleIsPostRead(index: Int) {
    val currentList = _resultLiveData.value ?: return
    val toggledPost = currentList[index]

    viewModelScope.launch {
      toggleIsPostReadUseCase(toggledPost)
    }
  }
}