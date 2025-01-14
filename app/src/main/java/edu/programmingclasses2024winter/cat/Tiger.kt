package edu.programmingclasses2024winter.cat

import javax.inject.Inject

class Tiger @Inject constructor(
  @RoarSound private val roarSound: String
) : Cat {
  override fun makeASound(): String = roarSound
}