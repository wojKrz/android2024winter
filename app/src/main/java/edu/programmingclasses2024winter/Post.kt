package edu.programmingclasses2024winter

data class Post(
  val id: Int,
  val userId: Int,
  val title: String,
  val body: String,
  val isRead: Boolean = false,
)
