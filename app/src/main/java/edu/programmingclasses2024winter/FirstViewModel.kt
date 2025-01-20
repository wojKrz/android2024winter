package edu.programmingclasses2024winter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.programmingclasses2024winter.FirstViewModel.Error.NoInternet
import edu.programmingclasses2024winter.OperationResult.Failure
import edu.programmingclasses2024winter.OperationResult.Success
import edu.programmingclasses2024winter.domain.ToggleIsPostReadUseCase
import edu.programmingclasses2024winter.domain.UpdatePostsUseCase
import edu.programmingclasses2024winter.net.PostsApi
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
  private val updatePostsUseCase: UpdatePostsUseCase,
  private val toggleIsPostReadUseCase: ToggleIsPostReadUseCase,
  private val postRepository: PostRepository
) : ViewModel() {

  private val _resultLiveData = MutableLiveData<List<Post>>()
  val resultLiveData: LiveData<List<Post>> = _resultLiveData

  private val _errorLiveData = MutableLiveData<Error>()
  val errorLiveData: LiveData<Error> = _errorLiveData

  init {
    viewModelScope.launch {
      postRepository
        .getAllPosts()
        .collect {
          _resultLiveData.value = it
        }
    }
  }

  fun getPosts() {
    viewModelScope.launch {
      when (updatePostsUseCase()) {
        is Success -> {}

        is Failure -> {
          _errorLiveData.value = NoInternet
        }
      }
    }
  }

  fun toggleIsPostRead(post: Post) {
    viewModelScope.launch {
      toggleIsPostReadUseCase(post)
    }
  }

  sealed class Error(
    val text: String
  ) {
    data object NoInternet : Error("NoInternet")
  }
}
