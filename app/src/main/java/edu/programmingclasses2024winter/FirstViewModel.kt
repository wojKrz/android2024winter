package edu.programmingclasses2024winter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.programmingclasses2024winter.FirstViewData.HasData
import edu.programmingclasses2024winter.FirstViewData.IsInProgress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FirstViewModel : ViewModel() {
  private val api = NetworkApi()

  private val _resultLiveData = MutableLiveData<FirstViewData>()
  val resultLiveData: LiveData<FirstViewData> = _resultLiveData

  fun makeNetworkCall() {
    viewModelScope.launch {
      _resultLiveData.value = IsInProgress
      val deferred = async(Dispatchers.IO) {
        delay(6000)
        api.getPosts()
      }
      _resultLiveData.value = deferred.await().run { HasData(this) }
    }
  }
}