package edu.programmingclasses2024winter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
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

  private val postsApi = retrofit.create(PostsApi::class.java)

  private val getPostsUseCase = GetPostsUseCase(
    Dispatchers.IO,
    postsApi
  )

  private val _resultLiveData = MutableLiveData<List<Post>>()
  val resultLiveData: LiveData<List<Post>> = _resultLiveData

  fun getPosts() {
    viewModelScope.launch {
      _resultLiveData.value = getPostsUseCase.invoke()
    }
  }
}
