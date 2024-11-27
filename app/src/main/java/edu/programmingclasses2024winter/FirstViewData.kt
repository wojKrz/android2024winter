package edu.programmingclasses2024winter

sealed class FirstViewData {
  data class HasData(val result: String): FirstViewData()
  data object IsInProgress: FirstViewData()
}
