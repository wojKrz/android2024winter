package edu.programmingclasses2024winter

import android.content.res.Resources
import javax.inject.Inject

interface Cat {
  fun getSound(): String
  val name: String
}

class Tiger @Inject constructor(
  private val resources: Resources,
  override val name: String
) : Cat {
  override fun getSound(): String = "${resources.getString(R.string.roar)} says $name"
}
