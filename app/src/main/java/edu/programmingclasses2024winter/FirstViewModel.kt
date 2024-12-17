package edu.programmingclasses2024winter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.programmingclasses2024winter.usecases.DownloadPostsUseCase
import edu.programmingclasses2024winter.usecases.ToggleIsPostReadUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException

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

  private val api = retrofit.create(NetworkApi::class.java)

  private val dao = MyApplication.database.postDao()

  private val mapper = PostMapper()

  private val repository = PostRepository(
    dao, mapper
  )

  private val downloadPostsUseCase = DownloadPostsUseCase(
    api, repository
  )

  private val toggleIsPostReadUseCase = ToggleIsPostReadUseCase(
    repository
  )

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