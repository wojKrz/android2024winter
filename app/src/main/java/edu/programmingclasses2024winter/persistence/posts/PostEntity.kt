package edu.programmingclasses2024winter.persistence.posts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
  tableName = "posts"
)
data class PostEntity(
  @PrimaryKey
  val id: Int,
  @ColumnInfo(name = "user_id")
  val userId: Int,
  val title: String,
  val body: String,
  val isRead: Boolean
)
