package edu.programmingclasses2024winter

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.programmingclasses2024winter.usecases.ToggleIsPostReadUseCase
import javax.inject.Inject

@HiltViewModel
class ThirdViewModel @Inject constructor(
  private val repository: PostRepository
): ViewModel() {
  init{
    Log.d("View model", "Third VM being created $repository")
  }
}