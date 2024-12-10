package edu.programmingclasses2024winter

data class Post(
  val userId: Long,
  val id: Long,
  val title: String,
  val body: String,
  val isRead: Boolean
)
