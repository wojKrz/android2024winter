package edu.programmingclasses2024winter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.programmingclasses2024winter.FirstViewModel.Error.NoInternet
import edu.programmingclasses2024winter.OperationResult.Failure
import edu.programmingclasses2024winter.OperationResult.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FirstViewModel : ViewModel() {

  private val loggingInterceptor = HttpLoggingInterceptor().also {
    it.level = HttpLoggingInterceptor.Level.BODY
  }

  private val netClient = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .build()
  private val retrofit = Retrofit.Builder()
    .baseUrl("https://jsonplaceholder.typicode.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(netClient)
    .build()

  private val postsDao = MyApplication.database.getPostsDao()

  private val postsApi = retrofit.create(PostsApi::class.java)

  private val postMapper = PostMapper()

  private val postRepository = PostRepository(
    postsDao, postMapper
  )

  private val updatePostsUseCase = UpdatePostsUseCase(
    Dispatchers.IO,
    postsApi,
    postRepository
  )

  private val toggleIsPostReadUseCase = ToggleIsPostReadUseCase(
    postRepository
  )

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
