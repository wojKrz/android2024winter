package edu.programmingclasses2024winter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
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

  private val api = retrofit.create(NetworkApi::class.java)

  private val _resultLiveData = MutableLiveData<FirstViewData>()
  val resultLiveData: LiveData<FirstViewData> = _resultLiveData

  fun makeNetworkCall() {
    viewModelScope.launch {
      val deferred = async(Dispatchers.IO) {
        api.getPosts()
      }
      _resultLiveData.value = deferred.await().run { FirstViewData(this) }
    }
  }

  fun toggleIsPostRead(index: Int) {
    val currentList = _resultLiveData.value?.result ?: return
    val toggledPost = currentList[index]
    val modifiedPost = toggledPost.copy(isRead = toggledPost.isRead.not())

    _resultLiveData.value = currentList.toMutableList()
      .also { modifiedList ->
        modifiedList[index] = modifiedPost
      }
      .toList()
      .run(::FirstViewData)
  }
}